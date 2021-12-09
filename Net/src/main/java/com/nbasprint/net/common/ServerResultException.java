package com.nbasprint.net.common;

/**
 * @ProjectName: SprintNBAMVVM
 * @Package: com.nbasprint.net.common
 * @ClassName: ServerResultException
 * @Description:
 * @Author: 张跃 企鹅：444511958
 * @CreateDate: 2021/12/6 8:07
 * @UpdateUser: 张跃
 * @UpdateDate: 2021/12/6 8:07
 * @UpdateRemark:
 * @Version: 1.0
 */
public class ServerResultException extends RuntimeException {
    public ServerResultException(String message) {
        super(message);
    }

    public ServerResultException(String message, Throwable cause) {
        super(message, cause);
    }
}
