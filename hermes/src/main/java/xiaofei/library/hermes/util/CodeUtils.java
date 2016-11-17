/**
 *
 * Copyright 2016 Xiaofei
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 *
 */

package xiaofei.library.hermes.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.google.gson.Gson;


/**
 * Created by Xiaofei on 16/4/9.
 */
public class CodeUtils {

    private static final Gson GSON = new Gson();
    private static byte[] outBytes;

    private CodeUtils() {

    }

    public static String encode(Object object) throws HermesException {
        if (object == null) {
            return null;
        } else {
            try {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ObjectOutputStream out = new ObjectOutputStream(baos);
                out.writeObject(object);
                outBytes = baos.toByteArray();
                System.out.println("CodeUtils encode:" + outBytes == null);
                // return outBytes;


                // JSONObject json = new JSONObject(object);
                // System.out.println("CodeUtils json:" + json.toString());
                System.out.println("CodeUtils Gson:" + GSON.toJson(object));
                return GSON.toJson(object);
            } catch (RuntimeException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            throw new HermesException(ErrorCodes.GSON_ENCODE_EXCEPTION, "Error occurs when Gson encodes Object " + object + " to Json.");
        }
    }

    public static <T> T decode(String data, Class<T> clazz) throws HermesException {
        try {
            System.out.println("CodeUtils decode:" + data);
            if (outBytes != null) {
                ByteArrayInputStream ins = new ByteArrayInputStream(outBytes);
                ObjectInputStream in = new ObjectInputStream(ins);
                T o = clazz.cast(in.readObject());
                System.out.println("CodeUtils decode:" + o.getClass());
                // return o;
            }

            return GSON.fromJson(data, clazz);
        } catch (RuntimeException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new HermesException(ErrorCodes.GSON_DECODE_EXCEPTION, "Error occurs when Gson decodes data of the Class " + clazz.getName());

    }



}
