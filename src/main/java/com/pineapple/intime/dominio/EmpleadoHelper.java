package com.pineapple.intime.dominio;

import java.util.Properties;

import javax.mail.Session;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.Key;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.commons.lang3.RandomStringUtils;

import com.steadystate.css.parser.ParseException;

public class EmpleadoHelper {
	
	private static final String UNICODE_FORMAT = "ISO-8859-1";	


	public static String generarContrasenna() throws Exception {
		String passHex = null;
		
		String passCifrada = cifra(RandomStringUtils.randomAlphanumeric(10));

		return passHex = ConvertirHexadecimal(passCifrada);
	}
	
	public static void sesionEmail(String emailDestino, String contrasenna) throws Exception {
		final String fromEmail = "intime.uclm.esi@gmail.com"; // requires valid gmail id
		final String password = "admin_1234"; // correct password for gmail id
		final String toEmail = emailDestino; // can be any email id
		String passNoHex = ConvertirCadena(contrasenna);
		String passDescifrada = descifra(passNoHex);

		System.out.println("TLSEmail Start");
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com"); // SMTP Host
		props.put("mail.smtp.port", "587"); // TLS Port
		props.put("mail.smtp.auth", "true"); // enable authentication
		props.put("mail.smtp.starttls.enable", "true"); // enable STARTTLS

		// create Authenticator object to pass in Session.getInstance argument
		Authenticator auth = new Authenticator() {
			// override the getPasswordAuthentication method
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(fromEmail, password);
			}
		};
		Session session = Session.getInstance(props, auth);
		
		sendEmail(session, toEmail, "Contraseña app InTime", "Su contraseña es:" + passDescifrada);
		
	}

	public static void sendEmail(Session session, String toEmail, String subject, String body) {
		try {
			MimeMessage msg = new MimeMessage(session);
			// set message headers
			msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
			msg.addHeader("format", "flowed");
			msg.addHeader("Content-Transfer-Encoding", "8bit");

			msg.setFrom(new InternetAddress("no_reply@example.com", "NoReply-JD"));

			msg.setReplyTo(InternetAddress.parse("no_reply@example.com", false));

			msg.setSubject(subject, "UTF-8");

			msg.setText(body, "UTF-8");

			msg.setSentDate(new Date());

			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
			System.out.println("Message is ready");
			Transport.send(msg);

			System.out.println("EMail Sent Successfully!!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static String ConvertirHexadecimal (String pass) {
		return String.format("%040x", new BigInteger(1, pass.getBytes()));
	}
	
	public static String ConvertirCadena(String hex) {
	    
	    String noHex= null;
		
	    String rephex = hex.replaceAll("^(00)+", "");
	    byte[] bytes = new byte[rephex.length() / 2];
	    for (int i = 0; i < rephex.length(); i += 2) {
	        bytes[i / 2] = (byte) ((Character.digit(rephex.charAt(i), 16) << 4) + Character.digit(rephex.charAt(i + 1), 16));
	    }

	    return noHex = new String(bytes);
	}
	


	public static String cifra(String sinCifrar) throws Exception {
		String cifrarString = null;
		final byte[] bytes = sinCifrar.getBytes("UTF-8");
		final Cipher aes = obtieneCipher(true);
		final byte[] cifrado = aes.doFinal(bytes);
		return cifrarString = new String(cifrado, UNICODE_FORMAT);
	}
	
	public static String descifra(String cifrado) throws Exception {
		String sinCifrar = null;
		final Cipher aes = obtieneCipher(false);
		byte[] cifradoByte = cifrado.getBytes(UNICODE_FORMAT);
		final byte[] bytes = aes.doFinal(cifradoByte);
		return sinCifrar = new String(bytes, "UTF-8");
	}
	
	private static Cipher obtieneCipher(boolean paraCifrar) throws Exception {
		KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
		keyGenerator.init(128);
		Key frase = keyGenerator.generateKey();
		String frase2 = frase.getFormat();
		final MessageDigest digest = MessageDigest.getInstance("SHA");
		digest.update(frase2.getBytes("UTF-8"));
		final SecretKeySpec key = new SecretKeySpec(digest.digest(), 0, 16, "AES");

		final Cipher aes = Cipher.getInstance("AES/ECB/PKCS5Padding");
		if (paraCifrar) {
			aes.init(Cipher.ENCRYPT_MODE, key);
		} else {
			aes.init(Cipher.DECRYPT_MODE, key);
		}

		return aes;
	}
	
	public String toString() {
		return null;
	}
	
	public static String CalculoTiempo (Object apertura, Object cierre) throws ParseException, java.text.ParseException {
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
		
		Date Dateapertura = dateFormat.parse((String) apertura);
		Date DateCierre = dateFormat.parse((String) cierre);
		
		float tiempo = (float) (((Dateapertura).getTime() - (DateCierre).getTime())/1000);
		float minutos = tiempo/60;
		
		String jornada = Float.toString(minutos);
		
		return jornada;
	}

}
