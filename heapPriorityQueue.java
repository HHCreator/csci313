import java.util.ArrayList;
import java.util.Comparator;

/** An implementation of a priority queue using an array-based heap. */

public class heapPriorityQueue<K,V> extends AbstractPriorityQueue<K,V> {

    /** primary collection of priority queue entries */
    
    protected ArrayList<Entry<K,V>> heap = new ArrayList<>();
    
    /** Creates an empty priority queue based on the natural ordering of its keys. */
    
    public heapPriorityQueue() { super(); }
    
    /** Creates an empty priority queue using the given comparator to order keys. */
    
    public heapPriorityQueue(Comparator<K> comp) { super(comp); }
    
    // protected utilities
    
    protected int parent(int j) { return (j-1) / 2; }
    
    protected int left(int j) { return 2*j + 1; }
    
    // truncating division
    
    protected int right(int j) { return 2*j + 2; }
    
    protected boolean hasLeft(int j) { return left(j) < heap.size(); }
    
    protected boolean hasRight(int j) { return right(j) < heap.size(); }
    
    /** Exchanges the entries at indices i and j of the array list. */
    
    protected void swap(int i, int j) {
    
    Entry<K,V> temp = heap.get(i);
    
    heap.set(i, heap.get(j));
    
    heap.set(j, temp);
    
    }
    
    /** Moves the entry at index j higher, if necessary, to restore the heap property. */
    
    // HW1: modify this
    protected int upheap(int j) { //j is index
        if(j==0){
            return 0;
        }else{
            int p = parent(j);
            if(compare(heap.get(j), heap.get(p)) < 0){
                swap(j,p);
                j=p;
                upheap(j);
                
            }
            return 1;
        }
    

   /* 
    while (j > 0) {
    
    // continue until reaching root (or break statement)
    
    int p = parent(j);
    
    if (compare(heap.get(j), heap.get(p)) >= 0) break;
    
    // heap property verified
    
    swap(j, p);
    
    j = p;
    */
    // continue from the parent's location
    
    }
    
    

     //∗∗Movestheentryat indexj lower, ifnecessary, torestoretheheapproperty.∗/
  protected int downheap(int j){// j is index
    if(!hasLeft(j)  ){// if out size then done
        return 0;
    }else{

        
        int leftIndex=left(j);
        int smallChildIndex=leftIndex;//set left is smaller
        
        if(hasRight(j)){
          int  rightIndex=right(j);
          if(compare(heap.get(leftIndex),heap.get(rightIndex))>0){//compare left to right if exist right
            smallChildIndex=rightIndex;//change smaller to right if right is smaller
          }
        }
        if(compare(heap.get(smallChildIndex),heap.get(j))<0){//check if child it is smaller
            swap(j,smallChildIndex);
            j = smallChildIndex;
            downheap(j);//if child is smaller, swap and dive
        }
        
        return 1;
    }


    /* 
    while(hasLeft(j)){ //continuetobottom(orbreakstatement)
        int leftIndex=left(j);
          int smallChildIndex=leftIndex; //althoughrightmaybesmaller
        if(hasRight(j)){
         int rightIndex=right(j);
         if(compare(heap.get(leftIndex),heap.get(rightIndex))>0)
            smallChildIndex=rightIndex; //rightchildissmaller
  }
  if(compare(heap.get(smallChildIndex),heap.get(j))>=0)
            break; //heappropertyhasbeenrestored
        swap(j, smallChildIndex);
        j = smallChildIndex; //continueatpositionofthechild
        }
        */

  }

   //publicmethods
  //∗∗Returnsthenumberof items inthepriorityqueue.∗/
  public int size(){return heap.size();}
  //∗∗Returns(butdoesnotremove)anentrywithminimalkey(ifany).∗/
  public Entry<K,V>min(){
  if(heap.isEmpty())return null;
  return heap.get(0);
  }
  //∗∗Insertsakey-valuepairandreturnstheentrycreated.∗/
 public Entry<K,V>insert(K key,V value)throws IllegalArgumentException{
  checkKey(key); //auxiliarykey-checkingmethod(couldthrowexception)
  Entry<K,V>newest=new PQEntry<>(key,value);
  heap.add(newest); //addtotheendofthelist
  upheap(heap.size()-1); //upheapnewlyaddedentry
  return newest;
  }
  //∗∗Removesandreturnsanentrywithminimalkey(ifany).∗/
  public Entry<K,V>removeMin(){
  if(heap.isEmpty())return null;
  Entry<K,V>answer=heap.get(0);
  swap(0,heap.size() -1); //putminimumitemattheend
  heap.remove(heap.size() -1); //andremoveit fromthelist;
  downheap(0); //thenfixnewroot
  return answer;
 }




 public static void main (String args[]){//main method
    heapPriorityQueue <Integer,String> myqueue = new heapPriorityQueue<>();
    myqueue.insert(5,"A");
    myqueue.insert(4, "B");
    myqueue.insert(7, "F");
    myqueue.insert(1, "D");
    myqueue.insert(3, "J");
    myqueue.insert(6, "L");
    myqueue.insert(8, "G");
    myqueue.insert(2, "H");

    myqueue.print();

   }
   public void print(){
    int i=0;
    while(i<heap.size()){
        System.out.println("key: "+ heap.get(i).getKey() +" value: " + heap.get(i).getValue() );
        i++;
    }
   }
    
}

