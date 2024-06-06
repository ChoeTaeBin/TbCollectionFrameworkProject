package summerProject;

/*
 * ��������� �ְ� ���� �ִ� �ڷᱸ��
 * dequeue�� queue�� ����� �����ϱ⶧���� �ϴ� dequeue���� ������
 * ���� priorityQueue�߰��� ���� TbQueue�������̽��� ���� ��ȹ��
 * 
 */

public interface TbDeque extends TbCollection{
	/*
	 * add�Լ���
	 */
	void addFirst(Object o);
	void addLast(Object o);
	
	/*
	 * peek�Լ��� : dequeue���� ��Ҹ� �������� �ʰ� ���� ��ȯ �ϴ� �Լ�
	 */
	Object peekFirst();
	Object peekLast();
	
	/*
	 * poll�Լ���: dequeue���� ��Ҹ� �����ϰ� ������ ���� ��ȯ�ϴ� �Լ�
	 */
	Object pollFirst();
	Object pollLast();
	
}
