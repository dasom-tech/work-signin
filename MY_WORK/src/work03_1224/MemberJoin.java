package work03_1224;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import work03_1224.MemberDAO;
import work03_1224.MemberVO;

public class MemberJoin {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		MemberDAO dao = new MemberDAO();
			
		System.out.println("회원가입을 위해 정보를 입력하시오>>>");
		System.out.println("1.아이디: ");
		String id = sc.nextLine();
		System.out.println("2.이름: ");
		String name = sc.nextLine();
		System.out.println("3.비밀번호: ");
		String password = sc.nextLine();
		System.out.println("4.이메일: ");
		String email = sc.nextLine();
		
		MemberVO member = new MemberVO(id, name, password, email);
		
		List<MemberVO> list = new ArrayList<>();
		list.add(member);
		
		dao.insertData(member);
		System.out.println("회원이 되신 것을 축하합니다.^^ ");
		System.out.println();
		
		System.out.println(">>로그인하세요~");
		boolean b = true;
		while(b) {
			for (MemberVO member1 : list) {
				System.out.println(">>아이디: ");
				id = sc.nextLine();
				System.out.println(">>비밀번호: ");
				password = sc.nextLine();
			
				if (password.equals(member1.getPassword())) {
					System.out.println("로그인 성공!!");
					System.out.println(member1);
					b = false;
				}else {
					System.out.println("비밀번호가 다릅니다. 다시 입력하세요.");
					continue;
				}
			}
		}
	}

}
