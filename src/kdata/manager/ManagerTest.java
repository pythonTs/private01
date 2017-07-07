package kdata.manager;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class ManagerTest {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		while(true) {
			System.out.println("1:추가 ,2:출력,3:검색 , 4:종료 ....");
			switch(sc.nextInt()) {
			case 1:
				System.out.println("학번을 입력하세요");
				String snum = sc.next();
				sc.nextLine();
				System.out.println(snum + "  "+snum.length());
				if (snum.length()!=8) { 
					System.out.println("학번을 정확히 입력하세요");
					break;
				}
				System.out.println("학생의 이름을 입력하세요");
				String sname = sc.nextLine();
				System.out.println("학생의 성별을 입력하세요");
				String sex = sc.nextLine();
				try {
					int result = StudentManager.insert(new Student(snum,sname,sex));
					if(result==0) {
						System.out.println("NO~~~~~~~~");
					}else {
						System.out.println("성공!");
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				System.out.println("");
				break;
			case 2:
				try {
					List<Student> list = StudentManager.selectAll();
					for( int i = 0;i<list.size();i++ ) {
						System.out.println(list.get(i));
					}
					/*for (Student l:list) {
						System.out.println(l.getSname());
					}*/
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("");
				break;
				
			case 3:
				System.out.println("검색하려는 학생 이름을 검색하세요");
				sc.nextLine();
				try {
					Student result = StudentManager.selectOne(sc.next());
					if(result ==null) {
						System.out.println("이름을 확인하고 다시 입력하세요\n");
						break;
					}
					System.out.println(result);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("");
				break;
			case 4: 
				System.out.println("종료합니다\n");
				return;
			default : 
				System.out.println("다시 입력하세요\n");

				break;
			}
		}
	}

}
