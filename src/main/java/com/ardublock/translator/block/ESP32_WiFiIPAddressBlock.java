package com.ardublock.translator.block;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;

public class ESP32_WiFiIPAddressBlock extends TranslatorBlock {
	
	public ESP32_WiFiIPAddressBlock(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	//@Override
	public String toCode() throws SocketNullException, SubroutineNotDeclaredException
	{
		
		String ret = "";
		TranslatorBlock tb = this.getRequiredTranslatorBlockAtSocket(0);
		String ipaddress = tb.toCode().replace("\"","").replace(".",",");
		tb = this.getRequiredTranslatorBlockAtSocket(1);
		String subnet = tb.toCode().replace("\"","").replace(".",",");
		tb = this.getRequiredTranslatorBlockAtSocket(2);
		String gateway = tb.toCode().replace("\"","").replace(".",",");
		tb = this.getRequiredTranslatorBlockAtSocket(3);
		String dns = tb.toCode().replace("\"","").replace(".",",");
		
		//Deal with line and character positioning
		translator.addSetupCommand(" IPAddress ESP32WiFiIPAddress("+ ipaddress +");");
		translator.addSetupCommand(" IPAddress ESP32WiFiSubnet("+ subnet +");");
		translator.addSetupCommand(" IPAddress ESP32WiFiDefaultGW("+ gateway +");");
		translator.addSetupCommand(" IPAddress ESP32WiFiDNS("+ dns +");");
		translator.addSetupCommand(" WiFi.config(ESP32WiFiIPAddress,ESP32WiFiSubnet,ESP32WiFiDefaultGW,ESP32WiFiDNS);");
		return ret;
	}
	
}


