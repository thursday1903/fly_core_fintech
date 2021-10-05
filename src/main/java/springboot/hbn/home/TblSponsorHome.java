package springboot.hbn.home;
// default package

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.ScrollMode;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;

import springboot.extra.entities.ViewSponsorStatistic;
import springboot.hbn.entities.TblSponsor;

// Generated May 21, 2021, 12:14:23 AM by Hibernate Tools 4.3.5.Final

/**
 * Home object for domain model class TblSponsor.
 * 
 * @see .TblSponsor
 * @author Hibernate Tools
 */

public class TblSponsorHome extends BaseHibernateHome {

	public List<TblSponsor> getListSponsorStatistic()
	{
		List<TblSponsor> lstList = new ArrayList<TblSponsor>();
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			Query query = session.createSQLQuery("SELECT t1.sponsor_id,t1.sponsor_name,\r\n"
					+ "count(case when t3.expertise_status = 116 then 1 end) as 'total_giai_ngan',\r\n"
					+ "count(case when t3.expertise_status = 122 then 1 end) as 'total_tat_toan'\r\n"
					+ "FROM tbl_sponsor t1\r\n"
					+ "INNER JOIN tbl_loan_sponsor_mapp t2 on t1.sponsor_id = t2.sponsor_id\r\n"
					+ "INNER JOIN tbl_loan_expertise_steps t3 on t2.loan_id = t3.loan_id\r\n"
					+ "GROUP BY t1.sponsor_id,t1.sponsor_name");
			query.setReadOnly(true);
			query.setFetchSize(Integer.MIN_VALUE); //MUST use Integer.MIN_VALUE, other value=fetch all
			ScrollableResults results = query.scroll(ScrollMode.FORWARD_ONLY);
			// iterate over results
			while (results.next()) {
				Integer sponsor_id = Integer.parseInt(results.get(0).toString());
				String sponsor_name = results.get(1).toString();
				Integer total_giai_ngan =  Integer.parseInt(results.get(2).toString());
			    Integer total_tat_toan =  Integer.parseInt(results.get(3).toString());
			    TblSponsor tblSponsor = new TblSponsor();
			    tblSponsor.setSponsorId(sponsor_id);
			    tblSponsor.setSponsorName(sponsor_name);
			    tblSponsor.setTotal_disbursement(total_giai_ngan);
			    tblSponsor.setTotal_settlement(total_tat_toan);
			    lstList.add(tblSponsor);
			}
			results.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return lstList;
	}
	
	public static void main(String[] args) {
		new TblSponsorHome().getListSponsorStatistic();
	}
}
