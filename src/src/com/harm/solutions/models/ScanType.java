package src.com.harm.solutions.models;

public enum ScanType {

	PreScanOnly("prescanonly"), PostScanOnly("postscanonly"), BothScans("bothscans");

	private ScanType(String scanType) {
		this.scanType = scanType;
	}

	private String scanType;

	private static ScanType getScanType;

	public ScanType getGetScanType() {
		return getScanType;
	}

	public void setGetScanType(ScanType getScanType) {
		this.getScanType = getScanType;
	}

	public static ScanType determineScanType(boolean prescan, boolean postscan) {
		if (onlyPostScan(prescan, postscan)) {
			return ScanType.PostScanOnly;
		} else if (onlyPreScan(prescan, postscan)) {
			return ScanType.PreScanOnly;
		} else if (bothScansPresent(prescan, postscan)) {
			return ScanType.BothScans;
		}
		return getScanType;
	}

	private static boolean onlyPostScan(boolean isPrescan, boolean isPostScan) {
		return !isPrescan && isPostScan;
	}

	private static boolean onlyPreScan(boolean isPrescan, boolean isPostScan) {
		return isPrescan && !isPostScan;
	}

	private static boolean bothScansPresent(boolean isPrescan, boolean isPostScan) {
		return isPrescan && isPostScan;
	}

}
