package Arellano_HW4;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.io.BufferedReader;
import java.util.Scanner;
import java.io.InputStream;
import java.io.FileInputStream;
public class Program2 {
public static void main(String[] args) throws IOException{
	int counter=0;
	Scanner in = new Scanner(System.in);
	String file1 = in.next();
	String file2 = in.next();
	File File = new File(file1);
	File File2 = new File(file2);
	Scanner FileOne = new Scanner(File);
	Scanner FileTwo = new Scanner(File2);
	ListNode ListNode = new ListNode();
	int service_time_total = Integer.parseInt(FileOne.nextLine().substring(0, 3));
	Node Node1 = new Node();
	Node1.service_time_total = service_time_total;
	FileOne.nextLine();
	String IDs1 = FileOne.nextLine().substring(12);
	Node1.id_number = Integer.parseInt(IDs1);
	String data1 = FileOne.nextLine().substring(14);
	String[] units1 = data1.split(":");
	int hours1 = Integer.parseInt(units1[0]);
	int minutes1 = Integer.parseInt(units1[1]);
	int seconds1 = Integer.parseInt(units1[2]);
	Node1.arrival_time = (hours1*3600)+(minutes1*60)+seconds1;
	ListNode.InsertLast(Node1);
	while (FileOne.hasNextLine()){
		FileOne.nextLine();	
		Node Node = new Node();
		Node.service_time_total = service_time_total;
		String IDs = FileOne.nextLine().substring(12);
		Node.id_number = Integer.parseInt(IDs);
		String data = FileOne.nextLine().substring(14);
		String[] units = data.split(":");
		int hours = Integer.parseInt(units[0]);
		if (hours>=counter){
			counter=hours;
		}
		else{
			hours=hours+12;
		}
		int minutes = Integer.parseInt(units[1]);
		int seconds = Integer.parseInt(units[2]);
		Node.arrival_time = (hours*3600)+(minutes*60)+seconds;
		ListNode.InsertLast(Node);
	}
	ListNode.CalculateTimeServed();
	ListNode.CalculateWaitTime();
	System.out.println(FileTwo.nextLine()+":"+ListNode.TotalCustomers());
	System.out.println(FileTwo.nextLine()+":"+MaximumBreakTime(ListNode));
	System.out.println(FileTwo.nextLine()+":"+TotalIdleTime(ListNode));
	System.out.println(FileTwo.nextLine()+":"+ListNode.MaxNumberOfPeopleinQueue());
	while (FileTwo.hasNextLine()){
		String line = FileTwo.nextLine();
		System.out.println(line.substring(0, 17)+":"+ListNode.find(Integer.parseInt(line.substring(16))).wait_time);
	}
}

public static int MaximumBreakTime(ListNode List){
	int break_time=0;
	int max_break =0;
	Node previous = List.first;
	Node current = List.first.next;
	while (current!=null){
		if (current.time_served>previous.finished_time){
			break_time = current.time_served-previous.finished_time;
			if (break_time>max_break){
				max_break=break_time;
			}
			else{
			previous= current;
			current=current.next;
			}
		}
		else{
			previous=current;
			current=current.next;
		}
	}
	return max_break;
}


public static int TotalIdleTime(ListNode List){
	int break_time=0;
	int total_break_time=0;
	Node previous = List.first;
	Node current = List.first.next;
	while (current!=null){
		if (current.time_served>previous.finished_time){
			break_time = current.time_served-previous.finished_time;
			total_break_time+=break_time;
			previous= current;
			current=current.next;
		}
		else{
			previous=current;
			current=current.next;
		}
	}
	return total_break_time;
}
}
