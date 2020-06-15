package com.papershare.papershare.service;

import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;

@Service
public class EmailService {

	@Value("${editor.mail}")
	private String editorsMail;

	private JavaMailSender javaMailSender;

	public EmailService(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}

	@Async
	public void sendPaperSubmissionToEditor(String author, String paperName, String coverLetterText)
			throws MailException, InterruptedException {

		System.out.println("Sending sendPaperSubmissionToEditor e-mail from " + author);
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(editorsMail);
		mail.setFrom("papershare.xml@gmail.com");
		mail.setSubject("Scientific paper submission by: " + author + ", paper title: " + paperName);
		mail.setText("You have new scientific paper submission by: " + author + ", tile of the paper: " + paperName
				+ "." + "\nCover letter:\n" + coverLetterText);
		javaMailSender.send(mail);
		System.out.println("E-mail endPaperSubmissionToEditor sent!");
	}

	@Async
	public void assignReviewForPaper(String reviewer, String paperName, String reviewersMail)
			throws MailException, InterruptedException {

		System.out.println("Sending assignReviewForPape e-mail from Editor");
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(reviewersMail);
		mail.setFrom("papershare.xml@gmail.com");
		mail.setSubject("Review assignment for paper: " + paperName);
		mail.setText("You have been assigned for review of scientific paper: " + paperName
				+ ", you can accept or decline the assigment.");
		javaMailSender.send(mail);
		System.out.println("E-mail assignReviewForPape sent!");
	}

	@Async
	public void acceptReviewForPaper(String reviewer, String paperName) throws MailException, InterruptedException {

		System.out.println("Sending acceptReviewForPaper e-mail from" + reviewer);
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(editorsMail);
		mail.setFrom("papershare.xml@gmail.com");
		mail.setSubject("Accepted review");
		mail.setText("Reviewer: " + reviewer + " accepted your review assignment for scientific paper: " + paperName);
		javaMailSender.send(mail);
		System.out.println("E-mail acceptReviewForPaper sent!");
	}

	@Async
	public void rejectReviewForPaper(String reviewer, String paperName) throws MailException, InterruptedException {

		System.out.println("Sending arejectReviewForPaper e-mail from" + reviewer);
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(editorsMail);
		mail.setFrom("papershare.xml@gmail.com");
		mail.setSubject("Rejected review");
		mail.setText("Reviewer: " + reviewer + " rejected your review assignment for scientific paper: " + paperName);
		javaMailSender.send(mail);
		System.out.println("E-mail rejectReviewForPaper sent!");
	}

	@Async
	public void finisheddReviewForPaper(String reviewer, String reviewId) throws MailException, InterruptedException {

		System.out.println("Sending finisheddReviewForPaper e-mail from" + reviewer);
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(editorsMail);
		mail.setFrom("papershare.xml@gmail.com");
		mail.setSubject("Finished review");
		mail.setText("Reviewer: " + reviewer + " has finished review: " + reviewId);
		javaMailSender.send(mail);
		System.out.println("E-mail finisheddReviewForPaper sent!");
	}

	@Async
	public void unitedReviewForPaper(String paperName, String authorsMail) throws MailException, InterruptedException {

		System.out.println("Sending unitedReviewForPaper e-mail from Editor");
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(authorsMail);
		mail.setFrom("papershare.xml@gmail.com");
		mail.setSubject("Reviews for paper: " + paperName);
		mail.setText("Reviews for your paper: " + paperName
				+ " are sent to you, send us back the paper when you are done with revisions.");
		javaMailSender.send(mail);
		System.out.println("E-mail unitedReviewForPaper sent!");
	}

	@Async
	public void publishedPaper(String paperName, String authorsMail) throws MailException, InterruptedException {

		System.out.println("Sending publishedPaper e-mail from Editor");
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(authorsMail);
		mail.setFrom("papershare.xml@gmail.com");
		mail.setSubject("Published paper");
		mail.setText("Congratz! Your paper: " + paperName + " has been published.");
		javaMailSender.send(mail);
		System.out.println("E-mail publishedPaper sent!");
	}

	@Async
	public void rejectedPaper(String paperName, String authorsMail) throws MailException, InterruptedException {

		System.out.println("Sending rejectedPaper e-mail from Editor");
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(authorsMail);
		mail.setFrom("papershare.xml@gmail.com");
		mail.setSubject("Rejected paper");
		mail.setText("We are sorry to inform you, your paper: " + paperName + " has been rejected.");
		javaMailSender.send(mail);
		System.out.println("E-mail rejectedPaper sent!");
	}
}
