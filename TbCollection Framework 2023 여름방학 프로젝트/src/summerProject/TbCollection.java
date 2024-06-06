package summerProject;

/*
 * TbList�� TbSet�� ���� ���� interface�̴�.
 * �� ���� ��� ���߾���� �⺻ ��ɿ� �ش��ϴ� �޼ҵ���� �����Ѵ�.
 */

public interface TbCollection {
	/*
	 * o�� ����� ��ü �Ǵ� c�� ����� ��ü ���θ� Collection�� �߰�
	 */
	boolean add(Object o);
	boolean addAll(TbCollection c);
	
	/*
	 * Collection�� ��� (��� ������ ����)
	 */
	void clear();
	
	/*
	 * o�� ����� ��ü�� �Ǵ� c�� ����� ��ü ���ΰ� Collection�� ���ԵǾ� �ִ����� �˻�
	 */
	boolean contains(Object o);
	boolean contains(TbCollection c);
	
	/*
	 * ����ִ��� Ȯ��
	 */
	boolean isEmpty();
	
	/*
	 * ����� ��ü ���� ��ȯ
	 */
	int size();
	
	/*
	 * Collection�� ����� ��ü�� Object�迭�� ����� ��ȯ �Ѵ�.
	 */
	Object[] toArray();
	
	/*
	 * Collection�� TbIterator�� ��ȯ�Ѵ�.
	 */
	TbIterator iterator();
}
