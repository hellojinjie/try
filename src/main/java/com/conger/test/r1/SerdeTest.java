package com.conger.test.r1;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


/*
 * Copyright (c) 2013 NeuLion, Inc. All Rights Reserved.
 */

public class SerdeTest
{

    /**
     * @param args
     */
    public static void main(String[] args) throws Exception
    {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ObjectOutputStream opt = new ObjectOutputStream(outputStream);
        long t1 = System.currentTimeMillis();
        int count = 300000;
        for (int i = 0; i < count; i++)
        {
            MessageObject message = new MessageObject();
            message.setMessageID(i);
            message.writeExternal(opt);
            // MessageObject message = MessageObject.createMessageObject();
//            opt.writeObject(message);
            // opt.reset();
        }

        long t2 = System.currentTimeMillis();
        // opt = new ObjectOutputStream(new ByteArrayOutputStream());

        System.out.println("time:" + (t2 - t1));
         if(1==1)
         {
         return;
         }
        ObjectInputStream input = new ObjectInputStream(new ByteArrayInputStream(
                outputStream.toByteArray()));
        t2 = System.currentTimeMillis();
        for (int i = 0; i < count; i++)
        {

             MessageObject message=(MessageObject)input.readObject();
             if(message.getMessageID()!=i)
             {
             throw new RuntimeException("ssss");
             }
//            try
//            {
//                new MessageObject().readExternal(input);
//            }
//            catch (Exception e)
//            {
//                System.out.println(i);
//                throw e;
//            }
        }
        long t3 = System.currentTimeMillis();
        System.out.println("time:" + (t3 - t2));
    }

}
