package CsProject;

/*
 * Copyright (c) 1995, 2013, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */ 
 


import java.net.*;
import java.io.*;

public class MultiServerThread extends Thread {
    private Socket socket = null;
    private Drop drop;
    
    public MultiServerThread(Socket socket, Drop drop) {
        super("MultiServerThread");
        this.socket = socket;
        this.drop = drop;
    }
    
    public void run() {

        try (
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());) {
            
                // Set up for output to clients
            Message outputLine;
            Object output;
            Object obj = null;
            Message message = null;
            
                // Protocol and process the empty message to establish connection
            ServerProtocol kkp = new ServerProtocol();
            outputLine = kkp.processInput(message);
            
                // Send out output
            out.writeObject(outputLine);

                // Take input
            while ((obj = in.readObject()) != null) {
                
            Message messagein = Message.class.cast(obj);
            
            // When Driver use the Add Ride method
            // Use synchronized object Drop to update the server's list of ALL rides accross ALL threads
            if(messagein.getRide()!=null)
            {
                Ride copy = messagein.getRide();
                drop.put(copy);                
            }
            
            // Process input as usual and get output
            output =  kkp.processInput(messagein);
            
            // Send output unless the message is end
            if (Message.class.cast(output).getMessage().equalsIgnoreCase("end"))
                    break;
                out.writeObject(output);
            }
            socket.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
