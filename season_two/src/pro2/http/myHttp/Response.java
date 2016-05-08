package pro2.http.myHttp;

import pro2.io.FileUtils;

import java.io.*;
import java.net.Socket;
import java.util.Date;

/**
 * 封装响应信息
 * Created by Near on 2016/1/26.
 */
public class Response {
    public static final String CRLF = "\r\n";
    public static final String BLANK = " ";

    /**
     * 响应头
     */
    private StringBuilder headInfo;
    /**
     * 响应正文
     */
    private StringBuilder context;
    /**
     * 正文字节长度
     */
    private int length;
    private Writer writer;

    public Response() {
        headInfo = new StringBuilder();
        context = new StringBuilder();
        length = 0;
    }

    public Response(Socket client) {
        this();
        try {
            this.writer = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
        } catch (IOException e) {
            e.printStackTrace();
            headInfo = null;
        }
    }

    public Response(OutputStream os) {
        this();
        writer = new BufferedWriter(new OutputStreamWriter(os));
    }

    private void createHeadInfo(int code) {
        //1)  HTTP协议版本、状态代码、描述
        headInfo.append("HTTP/1.1").append(BLANK).append(code).append(BLANK);
        switch (code) {
            case 200:
                headInfo.append("OK");
                break;
            case 404:
                headInfo.append("NOT FOUND");
                break;
            case 505:
                headInfo.append("SEVER ERROR");
                break;
        }
        headInfo.append(CRLF);
        //2)  响应头(Response Head)
        headInfo.append("Server:bjsxt Server/0.0.1").append(CRLF);
        headInfo.append("Date:").append(new Date()).append(CRLF);
        headInfo.append("Content-type:text/html;charset=UTF-8").append(CRLF);
        //正文长度：字节长度
        headInfo.append("Content-Length:").append(length).append(CRLF);
        //分隔符
        headInfo.append(CRLF);
    }

    // 构建正文
    public Response print(String str) {
        context.append(str);
        length += str.getBytes().length;
        return this;
    }

    // 构建正文+回车
    public Response println(String str) {
        context.append(str).append(CRLF);
        length += (str + CRLF).getBytes().length;
        return this;
    }

    public void pushToClient(int code) throws IOException {
        if (headInfo == null) {
            code = 500;
        }
        createHeadInfo(code);
        writer.append(headInfo.toString());
        writer.append(context.toString());
        writer.flush();
    }

    public void close() {
        FileUtils.close(writer);
    }
}
