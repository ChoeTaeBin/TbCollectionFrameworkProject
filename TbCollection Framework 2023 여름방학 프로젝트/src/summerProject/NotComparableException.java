package summerProject;

/*
 * comparable�� �������� ���� Ŭ������ ��ü�� ���Ϸ��� �Ҷ� �߻��ϴ� �����̴�.
 * RuntimeException�� ����Ͽ� �ݵ�� ó�������� �ʾƵ� �ȴ�.
 */

public class NotComparableException extends RuntimeException{
	NotComparableException(){
		super();
	}
	
	NotComparableException(Object[] o){
		super(o.getClass().getName()+"�� Comparable�� �������� ���� Ŭ���� �Դϴ�.");
	}
}
