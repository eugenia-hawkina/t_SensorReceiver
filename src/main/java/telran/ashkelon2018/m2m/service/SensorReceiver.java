package telran.ashkelon2018.m2m.service;


import java.io.IOException;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import telran.ashkelon2018.m2m.dto.Sensor;

@EnableBinding(Sink.class)
public class SensorReceiver {
	ObjectMapper mapper = new ObjectMapper();
	
	@StreamListener(Sink.INPUT)
	public void receiveSensorData(String sensorData) throws JsonParseException, JsonMappingException, IOException {
		Sensor sensor = mapper.readValue(sensorData, Sensor.class);
		long delay = System.currentTimeMillis() - sensor.getTimestamp();
		System.out.println("delay: " + delay + ", id: " + sensor.getId() 
			+ ", data: " + sensor.getData());
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
