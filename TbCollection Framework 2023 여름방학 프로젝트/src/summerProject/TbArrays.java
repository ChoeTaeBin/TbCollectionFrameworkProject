package summerProject;

/*
 * ���: �迭�� �ٷ�µ� �ʿ��� ��ɵ��� �����ϴ� Ŭ����
 * Ư¡: ��� �޼ҵ尡 static �޼ҵ��̴�.
 * 
 */
public class TbArrays {
	/*
	 * �迭�� ���ڿ��� ���� ��ȯ 
	 * ObjectŬ������ +toString() : String�� overriding�Ѱ� �ƴ�
	 * int���� Object���� ������
	 */
	public static String toString(int[] arr) {
		if(arr == null) return "null"; //arr�� null�̸� "null"�� return
		
		StringBuffer rStr = new StringBuffer("[");
		
		for(int i=0; i<arr.length; i++) {
			rStr.append(Integer.toString(arr[i]));
		
			if(i<arr.length-1) {
				rStr.append(", ");
			}
		}
		rStr.append("]");
		
		return rStr.toString();
	}
	
	public static String toString(Object[] arr) {
		if(arr == null) return "null"; //arr�� null�̸� "null"�� return
		
		StringBuffer rStr = new StringBuffer("[");
		
		for(int i=0; i<arr.length; i++) {
			if(arr[i] == null) {
				rStr.append("null");
			}else {
				rStr.append(arr[i].toString());
			}
			
			if(i<arr.length-1) {
				rStr.append(", ");
			}
		}
		rStr.append("]");
		
		return rStr.toString();
	}
	
	/*
	 * �� �迭�� ������ Ȯ���� �ִ� �޼ҵ�
	 * ������ -> ture, �ٸ��� ->false ��ȯ
	 * int���� Object���� ������
	 */
	public static boolean equals(int[] arr1, int[] arr2) {
		if(arr1 == arr2) return true; //���� �迭�̰ų� �Ѵ� null �̶�� true
		
		if(arr1 == null || arr2 == null) return false; //�ϳ��� null�� ������ false
		
		if(arr1.length != arr2.length) return false; //���̰� �ٸ��� �ٷ� false
		
		for(int i=0; i<arr1.length; i++) {
			if(arr1[i] != arr2[i]) return false;
		}
		
		return true;
	}
	
	public static boolean equals(Object[] arr1, Object[] arr2) {
		if(arr1 == arr2) return true; //���� �迭�̰ų� �Ѵ� null �̶�� true
		
		if(arr1 == null || arr2 == null) return false; //�ϳ��� null�̸� false
		
		if(arr1.length != arr2.length) return false; //���̰� �ٸ��� false
		
		for(int i=0; i<arr1.length; i++) {
			if(arr1[i]==null && arr2[i] != null) {
				return false;
			}else if(!arr1[i].equals(arr2[i])) {
				return false;
			}
		}
		
		return true;
	}
	
	/* �����迭�� ���ο� �迭�� ���̸� �Ѱܹ޾� ������ ���� ������ ���ο� �迭�� ���� ��ȯ�Ѵ�.
	 * ������ ���̺��� ���ο� �迭�� ���̰� �� ũ�ٸ� ���� �޺κ��� ����Ʈ������ ä������.
	 * int���� Object���� ������
	 * Object���� ��� Shallow Copy�� �̷������ ����
	 * throws: �����迭�� null -> NullPointerException, �䱸�� �迭ũ�Ⱑ ���� -> NegativeArraySizeException
	 */
	public static int[] copyOf(int[] orgArr, int requiredSize) {
		if(orgArr == null) {//orgArr�� null�̸� throw
			throw new NullPointerException();
		}
		
		if(requiredSize < 0) {//requiredSize�� �����̸� throw
			throw new NegativeArraySizeException();
		}
		
		int[] rArr = null; //��ȯ�� �迭
		rArr = new int[requiredSize];
	
		for(int idx=0; idx<requiredSize && idx<orgArr.length; idx++) {
			rArr[idx] = orgArr[idx];
		}
		return rArr;
	}
	
	
	public static Object[] copyOf(Object[] orgArr, int requiredSize) {
		if(orgArr == null) {//orgArr�� null�̸� throw
			throw new NullPointerException();
		}
		
		if(requiredSize < 0) {//requiredSize�� �����̸� throw
			throw new NegativeArraySizeException();
		}
		
		Object[] rArr = null; //��ȯ�� �迭
		rArr = new Object[requiredSize];
		
		for(int idx =0; idx<requiredSize && idx<orgArr.length; idx++) {
			rArr[idx] = orgArr[idx];
		}
		return rArr;
	}
	
	/* �����迭�� ���ο� �迭�� ����� ���ϴ� ������ �Ѱܹ޾� ������ ���� ������ ���ο� �迭�� ���� ��ȯ�Ѵ�.
	 * endBound < startIdx�̸� ���� �߻�, �䱸�� �ε����� ������ ������ �ε������� �� ũ�ٸ� ���� �޺κ��� ����Ʈ������ ä������.
	 * endBound �ε����� ����� ���� �������Կ� ����
	 * int���� Object���� ������
	 * Object���� ��� Shallow Copy�� �̷������ ����
	 * throws: �����迭�� null -> NullPointerException, endBound < startIdx -> IllegalArgumentException
	 */
	public static int[] copyOfRange(int[] orgArr, int startIdx, int endBound) {
		if(orgArr == null) {//orgArr�� null�̸� throw
			throw new NullPointerException();
		}
		
		int length = endBound - startIdx;
		
		if(length < 0) {//endBound<startIdx�� throw
			throw new IllegalArgumentException();
		}
		
		int[] rArr = null; //��ȯ�� �迭
		rArr = new int[length];
		
		for(int copyIdx=0, orgIdx = startIdx; copyIdx<length && orgIdx<orgArr.length; copyIdx++) {
			rArr[copyIdx] = orgArr[orgIdx++];
		}
		
		return rArr;
	}
	
	public static Object[] copyOfRange(Object[] orgArr, int startIdx, int endBound) {
		if(orgArr == null) {//orgArr�� null�̸� throw
			throw new NullPointerException();
		}
		
		int length = endBound - startIdx;
		
		if(length < 0) {//endBound<startIdx�� throw
			throw new IllegalArgumentException();
		}
		
		Object[] rArr = null; //��ȯ�� �迭
		rArr = new Object[length];
		
		for(int copyIdx=0, orgIdx = startIdx; copyIdx<length && orgIdx<orgArr.length; copyIdx++) {
			rArr[copyIdx] = orgArr[orgIdx++];
		}
		
		return rArr;
	}
	
	/*
	 * �Ѱܹ��� �迭�� �����Ѵ�.
	 * int���� object���� ������
	 * ����Ʈ �˰����� �̿���
	 * object���� ��� comparable�� �������� �ʾ��� ��� ���ܸ� �߻� ��Ų��.
	 * throws : �迭�� null -> NullPointerException, object�� comparable�� ����X -> NotComparableException
	 */
	public static void sort(int[] arr) {
		if(arr == null) {//arr�� null�̸� ���� �߻�
			throw new NullPointerException();
		}
		
		QuickSort.quickSort(arr,0,arr.length-1);
	}
	
	public static void sort(Object[] arr) {
		if(arr == null) {//arr�� null�̸� ���� �߻�
			throw new NullPointerException();
		}
		
		if(!(arr instanceof Comparable[])) {//Comparable�� �������� �ʾҴٸ� ���� �߻�
			throw new NotComparableException(arr);
		}
		
		QuickSort.quickSort((Comparable[])arr,0,arr.length-1);
	}
	
	/*
	 * ���� Ž���� �̿��ؼ� �Ѱܹ��� �迭���� �Ѱܹ��� ���� ������ ����� �ε����� ��ȯ �Ѵ�.
	 * �迭�� ������������ ���ĵ� �����϶� �ùٸ� ����� ���� �� �ִ�.
	 * �Ѱܹ��� ���� ������ ��Ұ� ������ �ִٸ� �� �� � �ε����� ��ȯ ���� �� �� ����.
	 * ���� ��ġ�ϴ� ��Ұ� ���ٸ� -1�� ��ȯ�Ѵ�.
	 * int���� object���� ������
	 * object���� ��� comparable�� �������� �ʾ��� ��� ���ܸ� �߻� ��Ų��.
	 * throws : �迭�� null -> NullPointerException, object�� comparable�� ����X -> NotComparableException
	 */
	public static int binarySearch(int[] arr, int keyValue) {
		if(arr == null) {//�迭�� null�̸� throw
			throw new NullPointerException();
		}
		
		int lowIdx = 0;
		int highIdx = arr.length-1;
		
		while(lowIdx<=highIdx) {
			int midIdx = (lowIdx+highIdx)/2;
			
			if(arr[midIdx]<keyValue) {
				lowIdx = midIdx+1; //midIdx���ϴ� ����
			}else if(keyValue<arr[midIdx]) {
				highIdx = midIdx-1; //highIdx�̻��� ����
			}else {//keyValue == arr[midIdx]�̸�
				return midIdx;
			}
		}
		return -1;
	}
	
	public static int binarySearch(Object[] arr, Object keyValue) {
		if(arr == null) {//�迭�� null�̸� throw
			throw new NullPointerException();
		}
		
		if(!(arr instanceof Comparable[])) {//Comparable�� �������� �ʾҴٸ� ���� �߻�
			throw new NotComparableException(arr);
		}
		
		int lowIdx = 0;
		int highIdx = arr.length-1;
		
		while(lowIdx<=highIdx) {
			int midIdx = (lowIdx+highIdx)/2;
			
			if(((Comparable)arr[midIdx]).compareTo(keyValue) < 0) {//arr[midIdx]�� �� �켱������ ������
				lowIdx = midIdx+1; //midIdx���ϴ� ����
			}else if(((Comparable)keyValue).compareTo(arr[midIdx]) < 0) {//keyValue�� �� �켱������ ������
				highIdx = midIdx-1; //highIdx�̻��� ����
			}else {//keyValue == arr[midIdx]�̸�
				return midIdx;
			}
		}
		return -1;
	}
}
