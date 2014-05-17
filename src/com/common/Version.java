package common;

public class Version {
	private final static String VERSION = "2.1.00";
	private final static String TITLE = "Mobile";
	public final static String NOT_UPDATE = "0"; // �Ƿ��Ѹ���
	public final static String UPDATED = "1"; // �Ƿ��Ѹ���
	private static String updateState = NOT_UPDATE; // �Ƿ��Ѹ���

	public static String getVersion() {
		return VERSION;
	}

	public static String getUpdateState() {
		return updateState;
	}

	public static void setUpdateState(String updateState) {
		Version.updateState = updateState;
	}

	public static void main(String[] args) {
		System.out.println(TITLE + " " + VERSION);
	}
}
