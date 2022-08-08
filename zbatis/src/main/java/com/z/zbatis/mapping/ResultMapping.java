package com.z.zbatis.mapping;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: yuanjz
 * @Date: 2022-08-04 16:25
 * @Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultMapping {
    private String column;
    private String property;
}
