package summerProject;
/*
 * Last In First Out�� Stack �ڷᱸ��
 * TbArrayList�� ����Ͽ� ����� �̿��Ѵ�.
 */
public class TbStack extends TbArrayList{
	/*
	 * ����ִ��� �˷��ش�.
	 */
	public boolean empty() {
		return isEmpty();
	}
	
	/*
	 * �� ���� ��Ҹ� �������� �ʰ� ��ȯ�Ѵ�.
	 */
	public Object peek() {
		return get(size()-1);
	}
	
	/*
	 * �� ���� ��Ҹ� �����ϰ� ��ȯ�Ѵ�.
	 */
	public Object pop() {
		return remove(size()-1);
	}
	
	/*
	 * ������ ��ü�� �����Ѵ�.
	 * �Ѱܹ��� ��ü�� �״�� ��ȯ�ϴ�.
	 */
	public Object push(Object item) {
		add(item);
		return item;
	}
	
	/*
	 * �Ѱܹ��� ��ü�� ã�Ƽ� ��ġ�� ��ȯ�Ѵ�.
	 * ��ġ�� ������ 1�̴�.
	 * ���� ���� ������ ��Ұ� �������� ���� ���������ִ� �ε����� ��ȯ�Ѵ�.
	 * ��ã���� -1�� ��ȯ�Ѵ�.
	 */
	public int search(Object o) {
		int position = size() - lastIndexOf(o);
		
		if(position<=size()) {
			return position;
		}
		return -1;
	}
}
