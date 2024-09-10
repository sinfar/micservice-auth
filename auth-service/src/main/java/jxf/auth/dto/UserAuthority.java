package jxf.auth.dto;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

/**
 * @author: Simon
 * @Data:2024/8/6
 */
@Data
public class UserAuthority  implements GrantedAuthority {
    private String authority;
    @Override
    public String getAuthority() {
        return authority;
    }
}
