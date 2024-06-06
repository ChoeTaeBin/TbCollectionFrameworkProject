package summerProject;

/*
 * List�� �ڷᱸ������ Collection�� �� ��ҿ� �����ϴ� ��ɵ��� �������ش�.
 */
public interface TbListIterator extends TbIterator{
	/*
	 * �о�� ��Ұ� �ִ��� �˻��ϴ� �޼ҵ�
	 */
	boolean hasPrevious();
	
	/*
	 * ���� ��Ҹ� �о�´�.
	 */
	Object previous();
	
	/*
	 * ��� �о�� ��Ҹ� �����Ѵ�.
	 * next(), previous()�� ȣ����� �ʾҴٸ� IllegalStateException�߻�
	 */
	void remove();
	
	/*
	 * ��� �о�� ��Ҹ� �Ѱܹ��� ��ҷ� ��ü�Ѵ�.
	 * next(), previous()�� ȣ����� �ʾҴٸ� IllegalStateException�߻�
	 */
	void set(Object o);
}
