package gr.upatras.mqtt.lab03;

import org.springframework.stereotype.Service;

@Service
public class TextService implements ITextService {

	public TextService () {
		super();
	}
	
	@Override
	public String publishText(String myText) {
		MyMqttPublisher mqttPublisher = new MyMqttPublisher();
		mqttPublisher.runClient(myText);
		return myText + "->has published.";
	}

}