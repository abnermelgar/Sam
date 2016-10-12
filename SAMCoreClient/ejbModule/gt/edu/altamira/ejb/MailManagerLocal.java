package gt.edu.altamira.ejb;

import java.util.List;

import javax.ejb.Local;

import gt.edu.altamira.sam.model.Attachment;

@Local
public interface MailManagerLocal {

	/***
	 * 
	 * @param sender
	 * @param recipients
	 * @param subject
	 * @param body
	 * @param cc
	 * @param bcc
	 * @param attach
	 */
	public void createMail(String sender, String recipients, String subject, String body, String cc, String bcc,
			Attachment attach);

	/***
	 * 
	 * @param sender
	 * @param recipients
	 * @param subject
	 * @param body
	 * @param cc
	 * @param bcc
	 * @param attachs
	 */
	public void createMailAttachments(String sender, String recipients, String subject, String body, String cc,
			String bcc, List<Attachment> attachs);

}
