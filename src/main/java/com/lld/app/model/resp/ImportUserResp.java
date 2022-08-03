package com.lld.app.model.resp;

import lombok.Data;

import java.util.List;
import java.util.Set;

/**
 * @author: Chackylee
 * @description:
 * @create: 2022-08-03 10:47
 **/
@Data
public class ImportUserResp {
    private Set<String> successId;

    private Set<String> errorId;
}
