package summerProject;

/*
 * �ߺ��� ���, ������� �����ϴ� �ڷᱸ�� 
 */

public interface TbList extends TbCollection{
	/*
	 * �Ѱܹ��� index�� �Ѱܹ��� ��ü�� ���� �Ǵ� Collection�� ���Ե� ��� ��ü�� index���� ������
	 * index�ڿ� �����ִ� ��ҵ��� �ڷ� �и�
	 */
	void add(int index, Object element);
	boolean addAll(int index, TbCollection c);
	
	/*
	 * �Ѱܹ��� index�� ����� ���� ��ȯ�Ѵ�.
	 */
	Object get(int index);
	
	/*
	 * ���� o�� ���� ����� ���� �ڿ��ִ� ���� index�� ��ȯ�Ѵ�.
	 */
	int lastIndexOf(Object o);
	
	/*
	 * listIterator�� ��ȯ�Ѵ�.
	 * index�� �Ѱ��ָ� �� ���� ���� �����Ѵ�.
	 */
	TbListIterator listIterator();
	TbListIterator listIterator(int index);
	
	/*
	 * index�� ����� ��ü�� �����ϰ� ��ȯ�Ѵ�.
	 */
	Object remove(int index);
	
	/*
	 * index ��ġ�� �Ѱܹ��� ��ü�� �����Ѵ�.
	 */
	Object set(int index, Object element);
	
	//void sort(Comparator c);
	
	/*
	 * fromIndex���� toIndex-1������ list�� ���� ��ȯ�ϴ�.
	 */
	TbList subList(int fromIndex, int toIndex);	
}
