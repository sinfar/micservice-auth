package jxf.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * @author: Simon
 * @Data:2024/8/6
 */
@Data
@AllArgsConstructor
public class UserDTO {
    private Long id;
    private String username;
    private String password;
    private boolean isEnabled;
    private List<String> roles;
}
