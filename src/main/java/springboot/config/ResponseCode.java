package springboot.config;

public class ResponseCode {

	public enum RpCode {
		// 200 Thành công
		// 99 Giao dịch pending
		// 11 Giao dịch thất bại
		// 101 Lỗi trong quá trình xử lý bên IMEDIATECH
		// 102 RequestId bị trùng
		// 103 Chữ ký không chính xác
		// 110 PartnerCode không chính xác
		// 111 PartnerCode đã bị xóa khỏi hệ thống
		// 112 PartnerCode chưa được kích hoạt
		// 113 Mã Operation là bắt buộc
		// 114 Mã Operation không chính xác
		// 115 Mã ngân hàng không chính xác
		// 116 Mã chi nhánh ngân hàng không chính xác
		// 117 Số tài khoản không hợp lệ
		// 118 Tên người nhận không hợp lệ
		// 119 Mã giao dịch bên đối tác gửi lên là bắt buộc
		// 120 Mã giao dịch bên đối tác gửi lên bị trùng
		// 121 Hệ thống không tìm thấy giao dịch
		// 122 Số tiền chuyển là bắt buộc
		// 123 Số tiền chuyển không hợp lệ
		// 124 Nội dung chuyển tiền không hợp lệ
		// 129 Không đủ hạn mức chi tiền hoặc đã hết hạn bảo lãnh

		SUCCESS(200), PENDING(99), TRS_FAIL(11), SYSTEM_ERROR(101), SYS_DUPLICATE_REQ_ID(102), SYS_INVALID_SIGN(
				103), SYS_INVALID_PARTNERCODE(110), SYS_PARTNERCODE_DETETED(111), SYS_PARTNERCODE_UNACTIVE(
						112), SYS_REQUIRE_OPERATION(113), SYS_INVALID_OPERATION(114), SYS_INVALID_BANKNO(
								115), SYS_INVALID_BANKBRANCH(116), SYS_INVALID_ACCNO(117), SYS_INVALID_ACCNAME(
										118), SYS_REQUIRE_REQUESTID(119), SYS_DUPLICATE_REFERENCE_ID(
												120), SYS_REQUEST_ID_NOT_FOUND(121), SYS_REQUIRE_REQUEST_AMT(
														122), SYS_INVALID_REQUEST_AMT(123), SYS_INVALID_MEMO(
																124), SYS_BALANCE_EXCEED(129), SYS_INVALID_REQUEST_TIME(
																		130), SYS_TIME_CLIENT_SERVER_TOMUCH_DIFFERENCE(
																				131),NO_FEE_ASSIGN_TO_PARTNER(
																						132);
		private int value;

		private RpCode(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}

		public static void main(String[] args) {
			// System.out.println(Pattern.compile("-?[0-9]+").matches("700000.0000"));
		}
	}

}
