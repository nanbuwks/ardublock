package com.ardublock.translator.block;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;

public class ESP32_SetSMTPServerBlock extends TranslatorBlock {
	
	public ESP32_SetSMTPServerBlock(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	//@Override
	public String toCode() throws SocketNullException, SubroutineNotDeclaredException
	{
		
		String ret = "";
		ret = "";
		TranslatorBlock tb = this.getRequiredTranslatorBlockAtSocket(0);
		String SmtpServer = tb.toCode();
		tb = this.getRequiredTranslatorBlockAtSocket(1);
		String SmtpPort = tb.toCode().replace("\"","");
		tb = this.getRequiredTranslatorBlockAtSocket(2);
		String SmtpUserName = tb.toCode();
		tb = this.getRequiredTranslatorBlockAtSocket(3);
		String SmtpUserPass = tb.toCode();
		tb = this.getRequiredTranslatorBlockAtSocket(4);
		String SmtpSender = tb.toCode();
		tb = this.getRequiredTranslatorBlockAtSocket(5);
		String SmtpSendInterval = tb.toCode().replace("\"","");
		
		//Deal with line and character positioning
                translator.addHeaderFile("WiFiClientSecure.h");
                translator.addHeaderFile("Mailer.h");
                translator.addDefinitionCommand("Mailer mail(" + SmtpUserName + " , " + SmtpUserPass + " , " + SmtpSender + " , " + SmtpPort + " , " + SmtpServer + ");");
                translator.addDefinitionCommand("unsigned long SmtpSendInterval = " + SmtpSendInterval + ";");





		return ret;
	}
	
}


