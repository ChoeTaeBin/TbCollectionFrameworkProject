package summerProject;

/*
 * �ϴ� �������� ���̴�.
 */
public interface TbIterator {
	/*
	 * �о�� ���� ��Ұ� �ִ��� �˻��ϴ� �޼ҵ�
	 */
	boolean hasNext();
	
	/*
	 * ������Ҹ� �о�´�.
	 */
	Object next();
	
	/*
	 * ��� �о�� ��Ҹ� �����Ѵ�.
	 * next()�� ȣ����� �ʾҴٸ� IllegalStateException�߻�
	 */
	void remove();
}
