package summerProject;

import java.util.NoSuchElementException;

/*
 * �ϴ� ������ ���� linkedList�� ������ ��ġ�� �κ��� ������ TbAbstractLsit�� ����
 */
public class TbArrayList implements TbList{
	static private final int DEFAULT_CAPACITY = 10; //�⺻ ũ��
	private Object[] arr;
	private int capacity;
	private int topIdx = -1; //�����Ͱ� ����� ������ index
	
	//constructor
	public TbArrayList() {
		this(DEFAULT_CAPACITY);
	}
	
	//ũ�⸦ �Ѱ� �޴� ���
	public TbArrayList(int capacity) {
		this.capacity = capacity;
		arr = new Object[capacity];
	}
	
	//������ collection�� �Ѱ� ���� ���
	public TbArrayList(TbCollection c) {
		this(c.size() + DEFAULT_CAPACITY); //collection�� ũ�� + �⺻ũ��� ����Ʈ ����
		addAll(c);
	}
	
	public boolean add(Object o){
		if(capacity-1 == topIdx) {//�迭�� ���� ã�ٸ�
			capacityUp(2*capacity);
		}
		
		arr[++topIdx] = o;
		return true; //���� false?
	}
	
	//index�� ���� ��ȯ index�� ������ ���̶�� ���� �߻�
	public Object get(int index){
		return arr[index];
	}
	
	//�Ѱܹ��� �÷����� ��� ���� arr�� �����Ѵ�.
	public boolean addAll(TbCollection c){
		TbIterator i = c.iterator();
		while(i.hasNext()) {
			add(i.next());
		}
		return true; //���� false?
	}
	
	
	/*
	 * iterator �ν��Ͻ��� ��ȯ�ϴ� �Լ���
	 * �ش� ������Ʈ������ ����Ʈ������ Iterator�� ListIterator�� ��� �������� �ʰ� 
	 * ListIterator�� �����ϴ� ���·� �ܼ�ȭ �ߴ�. 
	 * ������ Collection �������̽����� ������ �޼ҵ带 ���� �ؾ��ϱ� ������ iterator() �޼ҵ带 ������
	 */
	public TbIterator iterator() {
		return new ListItr();
	}
	
	public TbListIterator listIterator(){
		return new ListItr();
	}
	
	//�Ѱ� ���� index�� ���� ��ġ���ϴ� iterator�� ��ȯ
	public TbListIterator listIterator(int index){
		return new ListItr(index);
	}
	
	/*
	 * index�� �ִ� ��Ҹ� ������ 
	 */
	public Object remove(int index){
		rangeCheck(index);
		
		Object rData = arr[index]; //����� �α�
		
		//������ ��ܿ��� �����
		for(int i=index; i<topIdx; i++) {
			arr[i] = arr[i+1];
		}
		topIdx--; //topIdx����
		
		return rData;
	}
	
	/*
	 * index�� �ִ� ��Ҹ� �Ѱܹ��� ��ü�� ��ü�ϰ� ������ �ִ� ��ü�� ��ȯ��
	 */
	public Object set(int index, Object element){
		rangeCheck(index);
		
		Object rData = arr[index]; //����� �α�
		
		arr[index] = element; //�����
				
		return rData;
	}
	
	/*
	 * list�� ���� �޼ҵ�
	 */
	public void clear() {
		arr = null;
		topIdx = -1;
		capacity = 0;
	}
	
	/*
	 * �Ѱܹ��� ��ü�� �ִ��� �˻��ϴ� �Լ�
	 */
	public boolean contains(Object o) {
		TbListIterator it = new ListItr();
		while(it.hasNext()) {
			if(it.next().equals(o)) {
				return true;
			}
		}
		return false;
	}
	
	/*
	 * c�� ���Ե� ��� ��ü�� ����Ʈ�� ǥ�ԵǾ� �ִ��� �˻��ϴ� �Լ�
	 */
	public boolean contains(TbCollection c){
		TbIterator it = c.iterator(); 	
		while(it.hasNext()) {
			if(!contains(it.next())) {
				return false;
			}
		}	
		return true;
	}
	
	/*
	 * ��� �ִ��� �˻�
	 */
	public boolean isEmpty(){
		return topIdx<0;
	}
	
	/*
	 * ũ�⸦ ��ȯ�ϴ� �Լ�
	 */
	public int size() {
		return topIdx+1;
	}
	
	/*
	 * Object�迭�� ���� ��ȯ
	 */
	public Object[] toArray() {
		return TbArrays.copyOf(arr, topIdx+1);
	}
	
	/*
	 * �Ѱܹ��� �ڸ��� ��Ҹ� ���� �Ѵ�. �� �κ��� ��ĭ�� �ڷ� �̵��Ѵ�.
	 */
	public void add(int index, Object element) {
		if(index<0 || topIdx+1<index) {
			throw new IndexOutOfBoundsException();
		}
		
		if(capacity-1 == topIdx) {//�迭�� ���� ã�ٸ�
			capacityUp(2*capacity);
		}
		
		move(index,1);//index���� ��ĭ�� �ڷ� �δ�. topIdx�� capacity�� �˾Ƽ� �������ش�.
		
		arr[index] = element;
		
		//topIdx++;//topIdx ����
	}
	
	/*
	 * c�� ��� ���Ҹ� �Ѱܹ��� ��ġ���� list�� �����Ѵ�.
	 */
	public boolean addAll(int index, TbCollection c){
		move(index,c.size());//index���� size��ŭ �̵�
		
		int idx = index;
		TbIterator it = c.iterator();
		while(it.hasNext()) {
			arr[idx++] = it.next();
		}
		
		return true;//���� false?
	}
	
	/*
	 * ���� o�� ���� ����� �ε����� ���� ū �ε����� ��ȯ
	 */
	public int lastIndexOf(Object o){
		TbListIterator it = new ListItr(topIdx+1);
		
		int idx = topIdx;
		while(it.hasPrevious()) {
			if(it.previous().equals(o)) {
				return idx;
			}
			idx--;
		}
		return -1;
	}
	
	/*
	 * fromIndex���� toIndex-1���� subList�� ���� ��ȯ�Ѵ�.
	 */
	public TbList subList(int fromIndex, int toIndex){
		int size = toIndex-fromIndex;;
		
		if(size<0) {
			throw new IllegalStateException();
		}
		
		rangeCheck(toIndex);
		
		TbList rList = new TbArrayList(size+10); //10��ŭ �����ְ� ũ�⸦ ����
		
		TbIterator it = new ListItr(fromIndex);
		for(int idx=0; idx<size; idx++) {
			rList.add(it.next());
		}
		
		return rList;
	
	}
	
	/*
	 * ������ ����� private�޼ҵ��
	 */
	
	//�־��� ũ��� �뷮�� ������Ŵ
	private void capacityUp(int newCapacity) {
		arr = TbArrays.copyOf(arr, newCapacity);
		capacity = newCapacity;
	}
	
	//������ üũ �ϰ� ������ �����ٸ� ���ܸ� �߻� ��Ŵ
	private void rangeCheck(int index) {
		if(index<0 || topIdx < index) {
			throw new IndexOutOfBoundsException();
		}
	}
	
	//startIdx ���� ������ ��ĭ�� �ڷ� �̵�
	private void move(int startIdx, int step) {
		int endIdx = topIdx +step;//����
		
		if(capacity <= endIdx) {
			capacityUp(2*(endIdx-1)); //�ʿ��� ���� �ι�ũ��� Ȯ��
		}
		
		for(int i=endIdx; i>=startIdx; i--) {
			arr[i+step] = arr[i];
		}
		
		topIdx = endIdx;
	}
	
	private class ListItr implements TbListIterator{
		int cursor = 0; //���� ��ġ�� ǥ����
		int justReadIndex = -1; //��� ���� index remove, set �Լ��� ȣ�� �Ǹ� -1�� �ٲ�
		
		//constructor
		ListItr(){}
		
		ListItr(int index){
			cursor = index; //cursor�� ������
		}
		
		/*
		 *�о�� ���� �ִ��� Ȯ�� ���ִ� �Լ�
		 */	
		public boolean hasNext() {
			return cursor <= topIdx;
		}
		
		public boolean hasPrevious() {
			return 0 < cursor;
		}
		
		/*
		 *list�� ����� ���� ��ȯ �ϴ� �Լ�
		 *������ ������ �ڷ� ���� cursor�� ����� �ٸ���
		 *�ڷ� ����: cursor�� ���� ��ȯ
		 *������ ����: cursor �տ����� ��ȯ
		 *�о�� ���� ������ NoSuchElementException �� throw��
		 */
		public Object next() {
			if(!hasNext()) {
				throw new NoSuchElementException();
			}
			//cursor�� ��Ҹ� ��ȯ
			justReadIndex = cursor; 
			cursor++;
			return arr[justReadIndex];
		}
		
		public Object previous() {
			if(!hasPrevious()) {
				throw new NoSuchElementException();
			}
			//cursor ���� ��Ҹ� ��ȯ
			cursor--;
			justReadIndex = cursor;
			return arr[justReadIndex];
		}
		
		/*
		 * ���� �ֱٿ� �о� ���� ��Ҹ� �����ϴ� �Լ�
		 * �ֱٿ� �о�� ���� ���ٸ�(������ next()�� previous()�� ȣ����� �ʾҴٸ�) IllegalStateException�� throw 
		 */
		public void remove() {
			if(justReadIndex<0) {
				throw new IllegalStateException();
			}
			TbArrayList.this.remove(justReadIndex);
			cursor = justReadIndex; //cursor����
			justReadIndex = -1;
		}
		
		/*
		 * ���� �ֱٿ� �о� ���� ��Ҹ� �Ѱܹ��� ��ü�� ��ü�ϴ� �Լ�
		 * �ֱٿ� �о�� ���� ���ٸ�(������ next()�� previous()�� ȣ����� �ʾҴٸ�) IllegalStateException�� throw 
		 */
		public void set(Object o) {
			if(justReadIndex<0) {
				throw new IllegalStateException();
			}
			TbArrayList.this.set(justReadIndex,o);
			justReadIndex = -1;
		}
		
	}
}
