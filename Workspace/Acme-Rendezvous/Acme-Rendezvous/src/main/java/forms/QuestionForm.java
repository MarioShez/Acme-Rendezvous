package forms;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;

public class QuestionForm {
	
	private int id;
	private int rendezvousId;
	private String content;
	
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getRendezvousId() {
		return rendezvousId;
	}

	public void setRendezvousId(int rendezvousId) {
		this.rendezvousId = rendezvousId;
	}

	@SafeHtml(whitelistType = WhiteListType.NONE)
	@NotBlank
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}

}
