//∗∗Anabstractbaseclasstoassist implementationsofthePriorityQueueinterface.∗/

import java.util.Comparator;

public abstract class AbstractPriorityQueue<K,V>  implements PriorityQueue<K,V>{

     //----------------nestedPQEntryclass---------------
 protected static class PQEntry<K,V> implements Entry<K,V>{
     private K k; //key
     private V v; //value
    public PQEntry(K key,V value){
     k=key;
     v=value;
     }
     //methodsof theEntryinterface
     public K getKey(){return k;}
     public V getValue(){return v;}
     //utilitiesnotexposedaspartoftheEntryinterface
     protected void setKey(K key){k=key;}
     protected void setValue(V value){v=value;}
     }//-----------endofnested PQEntryclass---------

 //instancevariableforanAbstractPriorityQueue
  //∗∗Thecomparatordefiningtheorderingofkeys inthepriorityqueue.∗/
  private Comparator<K> comp;
  //∗∗Createsanemptypriorityqueueusingthegivencomparatortoorderkeys.∗/
  protected AbstractPriorityQueue(Comparator<K>c){comp=c;}
  //∗∗Createsanemptypriorityqueuebasedonthenaturalorderingof itskeys.∗/
  protected AbstractPriorityQueue(){this(new DefaultComparator<K>());}
  //∗∗Methodforcomparingtwoentriesaccordingtokey∗/
  protected int compare(Entry<K,V>a,Entry<K,V>b){
  return comp.compare(a.getKey(),b.getKey());
  }
  //∗∗Determineswhetherakeyisvalid.∗/
  protected boolean checkKey(K key)throws IllegalArgumentException{
  try{
  return(comp.compare(key,key)==0); //seeifkeycanbecomparedtoitself
  }catch(ClassCastException e){
  throw new IllegalArgumentException("Incompatiblekey");
  }
  }
  //∗∗Testswhetherthepriorityqueueisempty.∗/
  public boolean isEmpty(){return size()==0;}
  


}
