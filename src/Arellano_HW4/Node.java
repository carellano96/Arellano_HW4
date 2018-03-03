package Arellano_HW4;

public class Node {
int id_number;
int arrival_time;
int service_time_total;
int wait_time;
int finished_time;
int time_served;
Node next;
Node previous;

public Node(){
	next = null;
}

public Node(int arrival_time){
	this.arrival_time=arrival_time;
}
}
