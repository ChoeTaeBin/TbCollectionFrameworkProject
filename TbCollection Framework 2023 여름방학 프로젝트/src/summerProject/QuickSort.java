package summerProject;
/*
 * QuickSort ����� ������ �ִ� Ŭ����
 * int���� Comparable �迭�� ���� �� �� �ֵ��� ������
 * ������������ ���ĸ� ������
 */

final public class QuickSort {
	
	/*
	 * quick sort�� �����ϴ� �Լ� divide and conquer ����� �̿��Ѵ�.
	 */
	public static void quickSort(int[] arr, int leftIdx, int rightIdx) {
		if(leftIdx >= rightIdx) {//���� ����
			return;
		}
		
		int pivotIdx = partition(arr, leftIdx, rightIdx);
		
		quickSort(arr, leftIdx, pivotIdx-1); //���� ����
		quickSort(arr, pivotIdx+1, rightIdx); //������ ����
	}
	
	public static void quickSort(Comparable[] arr, int leftIdx, int rightIdx) {
		if(leftIdx >= rightIdx) {//���� ����
			return;
		}
		
		int pivotIdx = partition(arr, leftIdx, rightIdx);
		
		quickSort(arr, leftIdx, pivotIdx-1); //���� ����
		quickSort(arr, pivotIdx+1, rightIdx); //������ ����
	}
	
	
	/*
	 * pivot�� ���� �۰ų� ���� ���� ���� ���� .... 1 ����
	 * pivot�� ���� ū���� ������ ���� .... 2 ����
	 * �� ��ġ�ϴ� �Լ� �з� ������ ������ ���� pivot�� �ڸ����� �ε����� ��ȯ
	 */
	private static int partition(int[] arr, int leftIdx, int rightIdx) {
		
		if(4<=rightIdx-leftIdx) {//�迭�� ũ�Ⱑ 5�̻��̸�
			selectPivot(arr, leftIdx, rightIdx); //������ pivot�� �̾� �� �������� ��ġ��
		}
		
		int pivotValue = arr[rightIdx]; //pivot ��
		int smallerIdx = leftIdx -1; //1������ �� �ε���
		
		for(int curIdx = leftIdx; curIdx<rightIdx; curIdx++) {
			if(arr[curIdx] <= pivotValue) { //pivot�� ���� �۰ų� ���� ���� ã����
				/*1������ ���������� ��ĭ Ȯ���Ű�� curIdx�� ����� ���� �� �ڸ��� ���� swap��
				 * ����: �߰��� pivotValue���� �۰ų� ���� ��(arr[curIdx])�� 1�������� ������
				 * ���ڸ��� �ִ� arr[smallerIdx+1]���� 2���������� �̵�(�ڸ��� �����ֱ� ����)
				 */
				swap(arr,++smallerIdx,curIdx);
			}
		}
		swap(arr,++smallerIdx,rightIdx); //2������ ù��° ��Ұ� pivotValue�� �ڸ��̹Ƿ�
		
		return smallerIdx;
	}
	
	private static int partition(Comparable[] arr, int leftIdx, int rightIdx) {
		
		if(4<=rightIdx-leftIdx) {//�迭�� ũ�Ⱑ 5�̻��̸�
			selectPivot(arr, leftIdx, rightIdx); //������ pivot�� �̾� �� �������� ��ġ��
		}
		
		Comparable pivotValue = arr[rightIdx]; //pivot ��
		int smallerIdx = leftIdx -1; //1������ �� �ε���
		
		for(int curIdx = leftIdx; curIdx<rightIdx; curIdx++) {
			if(arr[curIdx].compareTo(pivotValue) <= 0 ) { //pivot�� ���� �켱������ ���ų� ���� ���� ã����
				/*1������ ���������� ��ĭ Ȯ���Ű�� curIdx�� ����� ���� �� �ڸ��� ���� swap��
				 * ����: �߰��� pivotValue���� �۰ų� ���� ��(arr[curIdx])�� 1�������� ������
				 * ���ڸ��� �ִ� arr[smallerIdx+1]���� 2���������� �̵�(�ڸ��� �����ֱ� ����)
				 */
				swap(arr,++smallerIdx,curIdx);
			}
		}
		swap(arr,++smallerIdx,rightIdx); //2������ ù��° ��Ұ� pivotValue�� �ڸ��̹Ƿ�
		
		return smallerIdx;
	}
	
	
	/*
	 * ������ ó�� �߰� �� �ε����� ����� ���� �켱������ �߰��� ���� �̾Ƽ� �� ���������� ������ �Լ�
	 * �ִ��� �߰����� pivot�� �����ؼ� ������� ������ ���̱� ����
	 */
	private static void selectPivot(int[] arr, int leftIdx, int rightIdx) {
		int idxArr[] = {leftIdx,(leftIdx+rightIdx)/2,rightIdx};
		
		//bubbleSort�̿�
		if(arr[idxArr[1]] < arr[idxArr[0]]) swap(idxArr,0,1);
		if(arr[idxArr[2]] < arr[idxArr[1]]) swap(idxArr,1,2);
		if(arr[idxArr[1]] < arr[idxArr[0]]) swap(idxArr,0,1);
		
		swap(arr, idxArr[1], rightIdx);//� ������ �߰��� ���� �� �����ʿ� ��ġ
	}
	
	private static void selectPivot(Comparable[] arr, int leftIdx, int rightIdx) {
		int idxArr[] = {leftIdx,(leftIdx+rightIdx)/2,rightIdx};
		
		//bubbleSort�̿�
		if(0 < arr[idxArr[0]].compareTo(arr[idxArr[1]])) swap(idxArr,0,1);
		if(0 < arr[idxArr[1]].compareTo(arr[idxArr[2]])) swap(idxArr,1,2);
		if(0 < arr[idxArr[0]].compareTo(arr[idxArr[1]])) swap(idxArr,0,1);
		
		swap(arr, idxArr[1], rightIdx);//� ������ �߰��� ���� �� �����ʿ� ��ġ
	}
	
	
	/*
	 * partition ������ ����� swap �Լ�
	 */
	private static void swap(int[] arr, int idx1, int idx2) {
		int temp = arr[idx1];
		arr[idx1] = arr[idx2];
		arr[idx2] = temp;
	}
	
	private static void swap(Comparable[] arr, int idx1, int idx2) {
		Comparable temp = arr[idx1];
		arr[idx1] = arr[idx2];
		arr[idx2] = temp;
	}
}
