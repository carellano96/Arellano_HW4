package Arellano_HW4;

public class ListNode {
Node first;

public ListNode(){
	first=null;
}

public void InsertFirst(Node node){
	node.next=first;
	first = node;	
}
public void InsertLast(Node node1){
	Node current = first;
	if (current==null){
		node1.next = current;
		first = node1;
	}
	else{
	while (current.next!=null){
		current=current.next;
	}
	Node temp = current.next;
	current.next = node1;
	node1.next=temp;
	}
}

public Node find(int id_number){
	Node current = first;
	while (current.id_number!=id_number){
		if (current.next==null){
			return null;
		}
		else{
		current = current.next;
		}
	}
	return current;
}
public int ListSize(){
	Node current= first;
	int counter = 0;
	while (current!=null){
		counter++;
		current=current.next;
	}
	return counter;
}






public int WaitingTime(Node node){
	int ArrivalTimePersonAhead = node.next.arrival_time;
	int FinishedTime = node.arrival_time+node.service_time_total;
	int waiting_time = FinishedTime - ArrivalTimePersonAhead;
	return waiting_time;
}

public void CalculateTimeServed(){//calculate this first;
	first.time_served = first.arrival_time;
	first.finished_time = first.arrival_time+first.service_time_total;
	Node previous=first;
	Node current=first;
	while (current!=null){
		if (current.arrival_time>=61200||previous.finished_time>=61200||previous.finished_time==0){
			current.time_served=0;
			current.finished_time=0;
			previous=current;
			current=current.next;
			continue;
		}
		else if (previous.arrival_time<32400&&current.arrival_time<32400){
			previous.time_served = 32400;
			previous.finished_time = previous.time_served+previous.service_time_total;
			current.time_served = previous.finished_time;
			current.finished_time = current.time_served+current.service_time_total;
			previous=current;
			current=current.next;			
			continue;
		}

		if (previous.finished_time>current.arrival_time){
		current.time_served = previous.time_served+previous.service_time_total;
		current.finished_time = current.time_served+current.service_time_total;
		previous=current;
		current=current.next;		}
		else{
			current.time_served = current.arrival_time;
			current.finished_time = current.time_served+current.service_time_total;
			previous=current;
			current=current.next;		}
		
	}
}
public void CalculateWaitTime(){
	Node current = first;
	Node previous = first;
	while (current!=null){
		if (previous.finished_time>=61200||previous.finished_time==0){
			if (current.arrival_time<61200){
				current.wait_time = 61200-current.arrival_time;
				previous=current;
				current=current.next;
				continue;
			}
			else{
			current.wait_time = 0;
			previous=current;
			current=current.next;
			continue;
			}
		}
		else{
		current.wait_time = current.time_served-current.arrival_time;
		previous = current;
		current=current.next;
		}
	}
}
public int MaxNumberOfPeopleinQueue(){
	int number_of_people=0;
	Node current = first;
	int max_number =0;
	while (current!=null){
		if (current.wait_time!=0){
			number_of_people+=1;
			current=current.next;
		}
		else{
			if (number_of_people>=max_number){
				max_number = number_of_people;
				number_of_people=0;
				current=current.next;
			}
			else{
				number_of_people=0;
				current=current.next;
			}
		}
	}
	return max_number;
}

public void PrintList(){
	Node current = first;
	while (current!=null){
		System.out.println("ID Number: "+current.id_number);
		System.out.println("Arrival Time: "+current.arrival_time);
		System.out.println("Time Served: "+current.time_served);
		System.out.println("Finished Serving at: "+current.finished_time);
		System.out.println("Wait Time: "+current.wait_time);
		System.out.println("Total time of service: "+current.service_time_total);
		System.out.println();
		current=current.next;
	}
}
public int TotalCustomers(){
	Node current =first;
	int counter =0;
	while (current!=null&&current.time_served!=0){
		counter++;
		current=current.next;
		
	}
	return counter;
}
public void DeleteNode(int id){
	Node current = first;
	Node previous = first;
	while (current.id_number!=id){
		previous=current;
		current=current.next;
	}
	if (current==first){
		first=first.next;
	}
	else{
		previous.next=current.next;
		
	}
}



}
