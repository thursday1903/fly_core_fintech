package springboot.hbn.entities;

// default package
// Generated May 29, 2021, 3:19:16 PM by Hibernate Tools 4.3.5.Final

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TblQuestions generated by hbm2java
 */
@Entity
@Table(name = "tbl_questions")
public class TblQuestions implements java.io.Serializable {

	private Integer question_id;
	private String question_content;
	private String question_short_desc;
	private Integer question_type;
	private Integer step_of_process;
	private String correct_answears;
	private Integer require_answear;
	private Integer active_status;
	private String available_answear;

	public TblQuestions() {
	}



	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "question_id", unique = true, nullable = false)
	public Integer getQuestionId() {
		return this.question_id;
	}

	public void setQuestionId(Integer questionId) {
		this.question_id = questionId;
	}

	@Column(name = "question_content", nullable = false, length = 500)
	public String getQuestionContent() {
		return this.question_content;
	}

	public void setQuestionContent(String questionContent) {
		this.question_content = questionContent;
	}

	@Column(name = "question_short_desc")
	public String getQuestionShortDesc() {
		return this.question_short_desc;
	}

	public void setQuestionShortDesc(String questionShortDesc) {
		this.question_short_desc = questionShortDesc;
	}

	@Column(name = "question_type")
	public Integer getQuestionType() {
		return this.question_type;
	}

	public void setQuestionType(Integer questionType) {
		this.question_type = questionType;
	}

	@Column(name = "step_of_process")
	public Integer getStepOfProcess() {
		return this.step_of_process;
	}

	public void setStepOfProcess(Integer stepOfProcess) {
		this.step_of_process = stepOfProcess;
	}

	@Column(name = "correct_answears", length = 500)
	public String getCorrectAnswears() {
		return this.correct_answears;
	}

	public void setCorrectAnswears(String correctAnswears) {
		this.correct_answears = correctAnswears;
	}

	@Column(name = "require_answear")
	public Integer getRequireAnswear() {
		return this.require_answear;
	}

	public void setRequireAnswear(Integer requireAnswear) {
		this.require_answear = requireAnswear;
	}

	@Column(name = "active_status")
	public Integer getActiveStatus() {
		return this.active_status;
	}

	public void setActiveStatus(Integer activeStatus) {
		this.active_status = activeStatus;
	}

	@Column(name = "available_answear")
	public String getAvailableAnswear() {
		return this.available_answear;
	}

	public void setAvailableAnswear(String availableAnswear) {
		this.available_answear = availableAnswear;
	}

}