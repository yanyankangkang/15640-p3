/**
 * 
 */
package mapreduce;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;


import dfs.YZFS;

/**
 * @author yinxu
 *
 */
public class MapReduceMaster {
	
	private static boolean ongoing = true;
	public static Queue<MapReduceTask> mapQueue = new LinkedList<MapReduceTask>();
	public static Queue<MapReduceTask> reduceQueue = new LinkedList<MapReduceTask>();
	public static AtomicInteger jobId = new AtomicInteger(0);
	public static HashMap<Integer, Integer> jobToTaskCount = new HashMap<Integer, Integer>();
	
	public static void main(String[] args) {
		
		ServerSocket ss;
		
		try {
			ss = new ServerSocket(YZFS.MP_SLAVE_PORT);
			while(ongoing) {
				Socket sock = ss.accept();
				MapReduceMasterThread masterThread = new MapReduceMasterThread(sock);
				masterThread.start();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
