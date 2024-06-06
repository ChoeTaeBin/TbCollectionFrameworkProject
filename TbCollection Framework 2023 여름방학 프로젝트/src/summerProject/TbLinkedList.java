package summerProject;
import java.util.NoSuchElementException;

//LinkedList

/*
 * linkedList�� ��� java�� api�� ���� �����ϸ鼭 �����ϸ鼭 ������
 */

public class TbLinkedList implements TbList, TbDeque{
	private Node first = null; //�� �պκ�
	private Node last = null; //�� ���κ�
	private int size = 0; //���� ũ�� (topIdx = size-1)

	/*
	 * constructor
	 */
	public TbLinkedList(){
		//empty
	}

	public TbLinkedList(TbCollection c) {
		addAll(c);
	}

	/*
	 * �⺻ add�Լ�, �ǵڿ� ���� data�� ��带 ���� ��ġ��Ų��.
	 */
	public boolean add(Object data){
		linkLast(data);
		return true;//���� false?
	}

	/*
	 * c�� ���Ե� ��� ��Ҹ� ����Ʈ�� �߰���Ŵ
	 */
	public boolean addAll(TbCollection c){
		TbIterator it = c.iterator();
		while(it.hasNext()) {
			add(it.next());
		}
		return true;//���� false?
	}

	/*
	 * ���� element�� ��带 ����� index��ü�� �����Ѵ�.
	 */
	public void add(int index, Object element) {
		if(index<0 || size<index) {
			throw new IndexOutOfBoundsException();
		}else if(size == index) {
			add(element);
		}else {
			linkBefore(element,node(index));
		}
	}

	/*
	 * c�� ��� ��Ҹ� index���� ä�� �ִ´�.
	 */
	public boolean addAll(int index, TbCollection c){
		if(index<0 || size<index) {
			throw new IndexOutOfBoundsException();
		}else if(index==size){//�� �ں��� �̾� ���̴� ���
			TbIterator it = c.iterator();

			while(it.hasNext()) {
				add(it.next());
			}
		}else {
			TbIterator it = c.iterator();

			if(it.hasNext()) { 
				Node cur = node(index); //null�� �ƴ�
				//�� ó�� �ϳ��� �տ� ����
				cur = linkBefore(it.next(),cur);
				while(it.hasNext()) {
					cur = linkAfter(it.next(),cur); //���Ĵ� �ڿ� ����
				}
			}
		}

		return true;
	}

	/*
	 * �⺻ get�Լ�, index�� �Ѱܹ޾� �ش� index�� ����� ���� ��ȯ�ϴ�.
	 */
	public Object get(int index){
		rangeCheck(index);

		return node(index).item;
	}

	/*
	 * �⺻ remove�Լ�, index�� �Ѱܹ޾� �ش� index�� ��带 �����Ѵ�.
	 */
	public Object remove(int index){
		rangeCheck(index);

		if(index == 0) { //�� �� �����̸�
			return unlinkFirst();
		}else if(size-1 == index) {
			return unlinkLast();
		}else {
			return unlink(node(index));
		}
	}

	/*
	 * ����Ʈ�� ���
	 */
	public void clear() {
		first = null;
		last = null;
		size = 0;
	}

	/*
	 * ����Ʈ�� ����ִ��� �˷���
	 */
	public boolean isEmpty(){
		return size <= 0;
	}

	/*
	 * ũ�⸦ ��ȯ
	 */
	public int size() {
		return size;
	}

	/*
	 * contains �Լ���
	 */
	public boolean contains(Object o) {
		TbIterator it = iterator();

		while(it.hasNext()) {
			if(it.next().equals(o)) return true;
		}
		return false;
	}

	public boolean contains(TbCollection c){
		TbIterator it = c.iterator();

		while(it.hasNext()) {
			if(!contains(it.next())) return false;
		}
		return true;
	}

	/*
	 * list�� object�迭�� ���� ��ȯ�Ѵ�.
	 */
	public Object[] toArray() {
		Object[] arr = new Object[size];

		int index = 0;
		TbIterator it = iterator();

		while(it.hasNext()) {
			arr[index++] = it.next();
		}

		return arr;
	}

	/*
	 * ���� o�� ����� ���� �ε����� ū ����� �ε����� ��ȯ�Ѵ�.
	 */
	public int lastIndexOf(Object o){
		if(get(size-1).equals(o)) return size-1; //�� �ڴ� ���� Ȯ��

		int index = size-2;
		TbListIterator it = listIterator(size-1);

		while(it.hasPrevious()) {
			if(it.previous().equals(o)) {
				return index;
			}
			index--;
		}

		return -1; //��ã���� -1 ��ȯ
	}


	/*
	 * iterator ��ü�� ��ȯ �ϴ� �Լ���
	 */
	public TbListIterator listIterator(){
		return new ListItr();
	}

	public TbListIterator listIterator(int index){
		return new ListItr(index);
	}

	public TbIterator iterator() {
		return listIterator();
	}

	/*
	 * index�� ��ġ�� �ִ� ����� ���� element�� ����� �����ִ� ���� ��ȯ�Ѵ�.
	 */
	public Object set(int index, Object element){
		rangeCheck(index);

		Node temp = node(index);

		Object rData = temp.item;//���

		temp.item = element;//�����

		return rData;
	}

	/*
	 * fromIndex���� toIndex-1���� subList�� ���� ��ȯ�Ѵ�.
	 */
	public TbList subList(int fromIndex, int toIndex){
		int size = toIndex-fromIndex;

		if(size<0) {
			throw new IllegalStateException();
		}

		rangeCheck(toIndex);

		TbList rList = new TbLinkedList();

		TbIterator it = new ListItr(fromIndex);
		for(int idx=0; idx<size; idx++) {
			rList.add(it.next());
		}

		return rList;
	}

	/*
	 * TbDeque�� �����ϱ� ���� �Լ���
	 */

	/*
	 * �Ѱܹ��� ���� ������ ��带 ����� �Ǿտ� �߰��Ѵ�.
	 */
	public void addFirst(Object o) {
		linkFirst(o);
	}

	/*
	 * add(Object o)�� ����� ����. 
	 */
	public void addLast(Object o) {
		add(o);
	}

	/*
	 * peek�Լ����̴�. ���� �Ǿ�, �ǵ��� ��带 ���������ʰ� �鿩�� ���⸸ �ϴ� ���̴�.
	 */
	public Object peekFirst(){
		if(!isEmpty()) {
			return node(0).item;
		}
		return null;
	}

	public Object peekLast(){
		if(!isEmpty()) {
			return node(size-1).item;
		}
		return null;
	}

	/*
	 * poll�Լ����̴�. ���� �Ǿ�, �ǵ��� ��带 �����ϰ� �� ���� ��ȯ�ϴ�.
	 */
	public Object pollFirst(){
		if(!isEmpty()) {
			return remove(0);
		}
		return null;
	}

	public Object pollLast(){
		if(!isEmpty()) {
			return remove(size-1);
		}
		return null;
	}

	/*
	 * ������ ���� private �Լ���
	 */

	//������ üũ �ϰ� ������ �����ٸ� ���ܸ� �߻� ��Ŵ
	private void rangeCheck(int index) {
		if(index<0 || size <= index) {
			throw new IndexOutOfBoundsException();
		}
	}

	/*
	 * �ش� �ε����� Node�� ��ȯ�Ѵ�. 
	 * �տ��� ���� ���� ������ �տ��� ���� �ڿ��� ���� ���� ������ �ڿ��� ���� �����Ѵ�.
	 * �ε��� �˻縦 ���� ����
	 * ������ ��� ���� first��ȯ
	 * size-1�̻��� index�� ������ last��ȯ
	 */
	private Node node(int index) {
		Node rNode; //��ȯ�� ���

		if(index < size/2) {//�տ��� ������
			rNode = first;
			for(int i=0; i<index; i++) {
				rNode = rNode.next;
			}
		}else {//�ڿ��� ������
			rNode = last;
			for(int i=0; i<size-1-index; i++) {
				rNode = rNode.prev;
			}
		}

		return rNode;
	}

	/*
	 * data�� ������ ���� ��带 ����� first�� ��ġ ��Ŵ
	 */
	private Node linkFirst(Object data) {
		Node nNode = new Node(null,data,first);

		if(first==null) {//����Ʈ�� ���������
			last = nNode; //first �� null�̸� last�� null�̴�.
		}else {//����Ʈ�� ���� �ʾ�����
			first.prev = nNode;
		}

		first = nNode;
		size++;

		return nNode;
	}

	/*
	 * data�� ������ ���� ��带 ����� tail�� ��ġ ��Ŵ
	 */
	private Node linkLast(Object data) {
		Node nNode = new Node(last,data,null);

		if(last==null) {//����Ʈ�� ���������
			first = nNode;
		}else {//����Ʈ�� ���� �ʾ�����
			last.next = nNode;
		}
		last = nNode;
		size++;

		return nNode;
	}

	/*
	 * ���� data�� ��带 ���� succ�տ� �����Ѵ�.
	 * succ�� null�� �ƴϾ���Ѵ�.
	 */
	private Node linkBefore(Object data, Node succ) {
		Node nNode = new Node(succ.prev,data,succ);

		if(succ.prev == null) {//�� �� ������ ���
			first = nNode;
		}else {
			succ.prev.next = nNode;
		}
		succ.prev = nNode;

		size++;

		return nNode;
	}

	/*
	 * ���� data�� ��带 ���� pred�ڿ� �����Ѵ�.
	 * pred�� null�� �ƴϾ���Ѵ�.
	 */
	private Node linkAfter(Object data, Node pred) {
		Node nNode = new Node(pred,data,pred.next);
		if(pred.next == null) {
			last = nNode;
		}else {
			pred.next.prev = nNode;
		}
		pred.next = nNode;

		size++;

		return nNode;

	}
	/*
	 * first�� �����ϰ� ����� ���� ��ȯ
	 * first != null�̾�� ��
	 */
	private Object unlinkFirst() {
		Node rNode = first;

		if(first == last) { //��Ұ� �ϳ��� ���
			last = null;
		}else {
			first.next.prev = null;
		}
		first = first.next;

		size--;
		return rNode.item;
	}

	/*
	 * last�� �����ϰ� ����� ���� ��ȯ
	 * last != null�̾�� ��
	 */
	private Object unlinkLast() {
		Node rNode = last;

		if(last == first) { //��Ұ� �ϳ��� ���
			first = null;
		}else {
			last.prev.next = null;
		}
		last = last.prev;

		size--;
		return rNode.item;
	}

	/*
	 * null�� �ƴ� rNode�� ����Ʈ���� �����ϰ� �װ��� ��ȯ�Ѵ�.
	 */
	Object unlink(Node rNode) {

		//�� ����
		if(rNode.prev == null) {//rNode == first
			first = first.next;
		}else {
			rNode.prev.next = rNode.next;
		}

		//�� ����
		if(rNode.next == null) {//rNode == last
			last = last.prev;
		}else {
			rNode.next.prev = rNode.prev;
		}

		size--;
		return rNode.item;
	}

	/*
	 * �⺻ ���� ������ ��� Ŭ����
	 */
	private static class Node{
		Object item;//���빰
		Node next;//���� ���
		Node prev;//�� ���

		Node(Node prev, Object element, Node next) {
			this.item = element;
			this.next = next;
			this.prev = prev;
		}
	}

	private class ListItr implements TbListIterator{
		Node cursor = first; // ���� ���, default��: first
		Node justReadNode = null; //��� ���� node remove, set �Լ��� ȣ�� �Ǹ� null�� �ٲ�

		/*
		 * constructor
		 */
		public ListItr() {
			this(0);
		}

		public ListItr(int index){
			TbLinkedList.this.rangeCheck(index);
			cursor = node(index);
		}

		/*
		 * ���� �о�� ���� �ִ��� Ȯ���ϴ� �Լ�
		 */
		public boolean hasNext() {
			return (cursor != null);
		}

		public boolean hasPrevious() {
			return (cursor.prev != null);
		}


		/*
		 *list�� ����� ���� ��ȯ �ϴ� �Լ�
		 *������ ������ �ڷ� ���� cursor�� ����� �ٸ���
		 *�ڷ� ����: cursor�� ���� ��ȯ
		 *������ ����: cursor �տ����� ��ȯ
		 *�о�� ���� ������ NoSuchElementException �� throw��
		 */
		public Object next() {
			if(cursor == null) {
				throw new NoSuchElementException();
			}

			justReadNode = cursor;
			cursor = cursor.next;

			return justReadNode.item;
		}

		public Object previous() {
			cursor = cursor.prev;

			if(cursor == null) {
				throw new NoSuchElementException();
			}

			justReadNode = cursor;

			return justReadNode.item;
		}

		/*
		 * ���� �ֱٿ� �о� ���� ��Ҹ� �����ϴ� �Լ�
		 * �ֱٿ� �о�� ���� ���ٸ�(������ next()�� previous()�� ȣ����� �ʾҴٸ�) IllegalStateException�� throw 
		 */
		public void remove() {
			if(justReadNode == null) {
				throw new IllegalStateException();
			}
			TbLinkedList.this.unlink(justReadNode);
			justReadNode = null;
		}

		/*
		 * ���� �ֱٿ� �о� ���� ��Ҹ� �Ѱܹ��� ��ü�� ��ü�ϴ� �Լ�
		 * �ֱٿ� �о�� ���� ���ٸ�(������ next()�� previous()�� ȣ����� �ʾҴٸ�) IllegalStateException�� throw 
		 */
		public void set(Object o) {
			if(justReadNode == null) {
				throw new IllegalStateException();
			}
			justReadNode.item = o;
			justReadNode = null;
		}
	}
}
