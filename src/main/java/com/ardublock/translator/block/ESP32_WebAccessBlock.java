package com.ardublock.translator.block;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;

public class ESP32_WebAccessBlock extends TranslatorBlock {
	
	public ESP32_WebAccessBlock(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	//@Override
	public String toCode() throws SocketNullException, SubroutineNotDeclaredException
	{


		TranslatorBlock tb = this.getRequiredTranslatorBlockAtSocket(0);
		String host = tb.toCode();
		tb = this.getRequiredTranslatorBlockAtSocket(1);
		String path = tb.toCode();
                String ret = " WiFiClient socket;"

+    "const int httpPort = 80;\n"
+    "const char* host = "+host+";\n"
+    "if (!socket.connect(host, httpPort)) {\n"
+    "    Serial.println(\"connection failed\");\n"
+    "    return;\n"
 +   "}\n"
+"\n"
+    "// We now create a URI for the request\n"
+    "String url = "+path+";\n"
+"\n"
+    "Serial.print(\"Requesting URL: \");\n"
+    "Serial.println(url);\n"
+"\n"
+    "// This will send the request to the server\n"
+    "socket.print(String(\"GET \") + url + \" HTTP/1.1\\r\\n\" +\n"
+    "             \"Host: \" + host + \"\\r\\n\" +\n"
+    "             \"Connection: close\\r\\n\\r\\n\");\n"
+    "unsigned long timeout = millis();\n"
+    "while (socket.available() == 0) {\n"
+    "    if (millis() - timeout > 5000) {\n"
+    "        Serial.println(\">>> Client Timeout !\");\n"
+    "        socket.stop();\n"
+    "        return;\n"
+    "    }\n"
+    "}\n"
+"\n"
+    "// Read all the lines of the reply from server and print them to Serial\n"
+    "while(socket.available()) {\n"
+    "    String line = socket.readStringUntil('\\r');\n"
+    "    Serial.print(line);\n"
+    "}\n"
+"\n"

+    "Serial.println();\n"
+    "Serial.println(\"closing connection\");\n";


		return ret;
	}
	
}


