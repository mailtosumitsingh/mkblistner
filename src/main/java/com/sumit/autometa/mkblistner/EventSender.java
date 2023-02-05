package com.sumit.autometa.mkblistner;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

@Component
public class EventSender {
    ZMQ.Socket socket;
    ZContext context;

    public EventSender() {
        context = new ZContext();
		 socket = context.createSocket(SocketType.REQ);
        socket.connect("tcp://127.0.0.1:5550");

    }

    ObjectMapper mapper = new ObjectMapper();

    public void sendEvent(Point p) {
        try {
            String s = mapper.writeValueAsString(p);
          //  System.out.println(s);
			sendEventZMQ(s);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    private void sendEventZMQ(String s) {
        socket.send(s.getBytes(ZMQ.CHARSET), 0);
		byte[] reply = socket.recv(0);
		String ret = new String(reply, ZMQ.CHARSET);
		System.out.println(ret);
	}

}
