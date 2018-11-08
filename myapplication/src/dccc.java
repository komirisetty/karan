 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.cloudbus.cloudsim.Cloudlet;
import org.cloudbus.cloudsim.CloudletSchedulerTimeShared;
import org.cloudbus.cloudsim.Datacenter;
import org.cloudbus.cloudsim.DatacenterBroker;
import org.cloudbus.cloudsim.DatacenterCharacteristics;
import org.cloudbus.cloudsim.Host;
import org.cloudbus.cloudsim.Pe;
import org.cloudbus.cloudsim.Storage;
import org.cloudbus.cloudsim.UtilizationModel;
import org.cloudbus.cloudsim.UtilizationModelFull;
import org.cloudbus.cloudsim.Vm;
import org.cloudbus.cloudsim.VmAllocationPolicySimple;
import org.cloudbus.cloudsim.VmSchedulerTimeShared;
import org.cloudbus.cloudsim.core.CloudSim;
import org.cloudbus.cloudsim.provisioners.BwProvisionerSimple;
import org.cloudbus.cloudsim.provisioners.PeProvisionerSimple;
import org.cloudbus.cloudsim.provisioners.RamProvisionerSimple;
import org.cloudbus.cloudsim.File;
import org.cloudbus.cloudsim.HarddriveStorage;
import org.cloudbus.cloudsim.ParameterException;


/**
 *
 * @author nagesh
 */
@WebServlet(name = "dccc", urlPatterns = {"/dccc"})
public class dccc extends HttpServlet {
    private static List<Cloudlet> cloudletList;
    private static List<Vm> vmlist;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws java.lang.ClassNotFoundException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException, ParameterException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String mms = (String) request.getAttribute("stm");
        String i = (String) request.getAttribute("astate");
            String rdfname = (String) request.getAttribute("namef");
            String chi = (String) request.getAttribute("fai");
            int p=0,q=0,r=0;
             double fsize;
                                fsize = (Double) request.getAttribute("fsiz");
            String filepath = "D:/upload/";
             HarddriveStorage hd1;
             int crate = 0;
             int c=0,d=0;
        try {
            Class.forName("com.mysql.jdbc.Driver");
     Connection con = DriverManager.getConnection("jdbc:mysql://localhost/nageshdb","root","nagesh");   
     Statement st = con.createStatement();
     ResultSet rsm = st.executeQuery("select * from ddr");
    
    
   while(rsm.next())
   {
            crate = rsm.getInt("rate");
   }
    ResultSet rsd = st.executeQuery("select * from dins");
   
       while(rsd.next())
   {
            d=d+1;
   }
        ResultSet rsi = st.executeQuery("select * from ins");
         while(rsi.next())
   {
          c=c+1;
   }
            /* TODO output your page here. You may use following sample code. */
            // First step: Initialize the CloudSim package. It should be called
			// before creating any entities.
			int num_user = 1; // number of cloud users
			Calendar calendar = Calendar.getInstance();
			boolean trace_flag = false; // mean trace events
			// Initialize the CloudSim library
			CloudSim.init(num_user, calendar, trace_flag);
                        LinkedList<Storage> storagelist1 = new LinkedList<Storage>();
                         if("iup".equals(i))
                        {
                           p=p+1;
                            if(crate < 50 || crate == 50)
                            {
                                
                              hd1 = new HarddriveStorage(fsize);
                             File file = new File("D:/upload/"+rdfname+"", (int) fsize);
                             hd1.addFile(file);
                            storagelist1.add(hd1);
                              }
                            else
                            {
                                if(chi.equals("ndup"))
                                        {
                                            
                                        hd1 = new HarddriveStorage(fsize);
                             File file = new File("D:/upload/"+rdfname+"", (int) fsize);
                            hd1.addFile(file);
                            storagelist1.add(hd1);
                            
  
                                       }
                 
                            }
                        }
                        else
                        {
                            if("idown".equals(i))
                            {
                              if(chi.equals("dow"))
                              {
                                  hd1 = new HarddriveStorage(fsize);
                               File file = hd1.getFile("D:/upload/"+rdfname+"");  
                                storagelist1.add(hd1);
   
                       

                             }
r = r+1;

                            }
                            else
                            {
                             if(chi.equals("del"))
                             {
                                 hd1 = new HarddriveStorage(fsize);
                                 hd1.deleteFile("D:/upload/"+rdfname+"");
                                // storagelist1.add(hd1);
                                 q=q+1;
                                       }
                            }
                        }
                         Datacenter datacenter1 = createDatacenter("Datacenter_1",storagelist1);
                        // File fills = new File("D:/upload/"+rdfname+"", (int) fsize);
                         //datacenter1.addFile(fills);
			// Third step: Create Broker
			DatacenterBroker broker = createBroker();
			int brokerId = broker.getId();
                        //datacenter0.addFile(file1);

			// Fourth step: Create one virtual machine
			vmlist = new ArrayList<Vm>();

			// VM description
			int vmid = 0;
			int mips = 1000;
			long size = 10000; // image size (MB)
			int ram = 512; // vm memory (MB)
			long bw = 1000;
			int pesNumber = 1; // number of cpus
			String vmm = "Xen"; // VMM name

			// create VM
			Vm vm = new Vm(vmid, brokerId, mips, pesNumber, ram, bw, size, vmm, new CloudletSchedulerTimeShared());

			// add the VM to the vmList
			vmlist.add(vm);

			// submit vm list to the broker
			broker.submitVmList(vmlist);

			// Fifth step: Create one Cloudlet
			cloudletList = new ArrayList<Cloudlet>();

			// Cloudlet properties
			int id = 0;
			long length = 400000;
			long fileSize = 300;
			long outputSize = 300;
			UtilizationModel utilizationModel = new UtilizationModelFull();

			Cloudlet cloudlet = new Cloudlet(id, length, pesNumber, fileSize, outputSize, utilizationModel, utilizationModel, utilizationModel);
			cloudlet.setUserId(brokerId);
			cloudlet.setVmId(vmid);

			// add the cloudlet to the list
			cloudletList.add(cloudlet);

			// submit cloudlet list to the broker
			broker.submitCloudletList(cloudletList);

			// Sixth step: Starts the simulation
			CloudSim.startSimulation();
                         CloudSim.stopSimulation();
List<Cloudlet> newList = broker.getCloudletReceivedList();
			
                      // public  int htm = Host.;
                        long vubw = vm.getCurrentAllocatedBw();
                        long vtbw = vm.getBw();
                        List<Double> alml = vm.getCurrentAllocatedMips();
                        
                        int tc = c+d;
                        int tdc = d;
                        float ndid = (crate*tdc)/100;
                        int naf =  (int) (tc-ndid);
                        int ub = naf;
                        int swl = naf;
                        int tb = 40;
                        int abw = tb-ub;
                        float latency = (float)swl/abw;

st.executeQuery("insert into mycsr values('"+ tc +"','"+ tdc +"','"+ crate +"','"+ latency +"')"); 
st.executeQuery("insert into csr values('"+ crate +"','"+ latency +"')");
            /* TODO output your page here. You may use following sample code. */
            if(p > 0)
                    {
                      request.setAttribute("mes", mms);
            request.getRequestDispatcher("uaccount.jsp").forward(request, response);   
                    }
            else
            {
                if(r > 0)
            {
             response.setContentType("APPLICATION/OCTET-STREAM");   
response.setHeader("Content-Disposition","attachment; filename=\"" + rdfname + "\"");   
  
FileInputStream fileInputStream = new FileInputStream(filepath + rdfname);  
            
int y;   
while ((y=fileInputStream.read()) != -1) {  
out.write(y);   
} 

fileInputStream.close();   
out.close(); 
request.setAttribute("mes", mms);
            request.getRequestDispatcher("uaccount.jsp").forward(request, response); 
            }
                else
                {
                    request.setAttribute("mes", mms);
            request.getRequestDispatcher("uaccount.jsp").forward(request, response); 
                }
  
            }
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet dccc</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>deduplication tate: " + crate + "</h1>");
            out.println("<h1>server workload: " + swl + "</h1>");
            out.println("<h1>available bandwidth: " + abw + "</h1>");
            out.println("<h1>latency: " + latency + "</h1>");
            out.println("</body>");
            out.println("</html>");
            
            
            
        } finally {
            out.close();
        }
    }
        private static Datacenter createDatacenter(String name, LinkedList<Storage> storageList) {

		// Here are the steps needed to create a PowerDatacenter:
		// 1. We need to create a list to store
		// our machine
		List<Host> hostList = new ArrayList<Host>();

		// 2. A Machine contains one or more PEs or CPUs/Cores.
		// In this example, it will have only one core.
		List<Pe> peList = new ArrayList<Pe>();

		int mips = 1000;

		// 3. Create PEs and add these into a list.
		peList.add(new Pe(0, new PeProvisionerSimple(mips))); // need to store Pe id and MIPS Rating

		// 4. Create Host with its id and list of PEs and add them to the list
		// of machines
		int hostId = 0;
		int ram = 2048; // host memory (MB)
		long storage = 1000000; // host storage
		int bw = 10000;

		
                        Host Host = new Host(
				hostId,
				new RamProvisionerSimple(ram),
				new BwProvisionerSimple(bw),
				storage,
				peList,
				new VmSchedulerTimeShared(peList));
		
hostList.add(Host);// This is our machine
 
		// 5. Create a DatacenterCharacteristics object that stores the
		// properties of a data center: architecture, OS, list of
		// Machines, allocation policy: time- or space-shared, time zone
		// and its price (G$/Pe time unit).
		String arch = "x86"; // system architecture
		String os = "Linux"; // operating system
		String vmm = "Xen";
		double time_zone = 10.0; // time zone this resource located
		double cost = 3.0; // the cost of using processing in this resource
		double costPerMem = 0.05; // the cost of using memory in this resource
		double costPerStorage = 0.001; // the cost of using storage in this
										// resource
		double costPerBw = 0.0; // the cost of using bw in this resource
		//LinkedList<Storage> StorageList = new LinkedList<Storage>(); // we are not adding SAN
													// devices by now
                //storageList.add(hd);

		DatacenterCharacteristics characteristics = new DatacenterCharacteristics(
				arch, os, vmm, hostList, time_zone, cost, costPerMem,
				costPerStorage, costPerBw);

		// 6. Finally, we need to create a PowerDatacenter object.
		Datacenter datacenter = null;
		try {
			datacenter = new Datacenter(name, characteristics, new VmAllocationPolicySimple(hostList), storageList, 0);
		} 
                
                catch (Exception e) {
			e.printStackTrace();
		}

		return datacenter;
	}

	// We strongly encourage users to develop their own broker policies, to
	// submit vms and cloudlets according
	// to the specific rules of the simulated scenario
	/**
	 * Creates the broker.
	 *
	 * @return the datacenter broker
	 */
	private static DatacenterBroker createBroker() {
		DatacenterBroker broker = null;
		try {
			broker = new DatacenterBroker("Broker");
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return broker;
	}

	/**
	 * Prints the Cloudlet objects.
	 *
	 * @param list list of Cloudlets
	 */
	private static void printCloudletList(List<Cloudlet> list) {
		int size = list.size();
		Cloudlet cloudlet;

		String indent = "    ";
		//Log.printLine();
		//Log.printLine("========== OUTPUT ==========");
		//Log.printLine("Cloudlet ID" + indent + "STATUS" + indent
				//+ "Data center ID" + indent + "VM ID" + indent + "Time" + indent
				//+ "Start Time" + indent + "Finish Time");

		DecimalFormat dft = new DecimalFormat("###.##");
		for (int i = 0; i < size; i++) {
			cloudlet = list.get(i);
			//Log.print(indent + cloudlet.getCloudletId() + indent + indent);

			if (cloudlet.getCloudletStatus() == Cloudlet.SUCCESS) {
				//Log.print("SUCCESS");

				//Log.printLine(indent + indent + cloudlet.getResourceId()
						//+ indent + indent + indent + cloudlet.getVmId()
						//+ indent + indent
						//+ dft.format(cloudlet.getActualCPUTime()) + indent
						//+ indent + dft.format(cloudlet.getExecStartTime())
						//+ indent + indent
						//+ dft.format(cloudlet.getFinishTime()));
			}
		}
	}

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(dccc.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(dccc.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParameterException ex) {
            Logger.getLogger(dccc.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(dccc.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(dccc.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParameterException ex) {
            Logger.getLogger(dccc.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
