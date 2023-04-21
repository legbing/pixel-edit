package com.ooad.pixeledit.dto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto
{
    private Long id;
    @NotEmpty
    private String userName;
    @NotEmpty
    private String fullName;
    @NotEmpty(message = "Email should not be empty")
    @Email
    private String email;
    @NotEmpty(message = "Password should not be empty")
    private String password;
    @NotEmpty 
    private String address;
    @NotEmpty 
    private String userType;

    public class CustomerDto extends UserDto {
        private int phoneNumber;
    
        public CustomerDto(Long id, String userName, String fullName, String email, String password, String address, String userType, int phoneNumber) {
            super(id, userName, fullName, email, password, address,userType);
            this.phoneNumber = phoneNumber;
        }
    
        public int getPhoneNumber() {
            return phoneNumber;
        }
    
        public void setPhoneNumber(int phoneNumber) {
            this.phoneNumber = phoneNumber;
        }
    }
    public class PhotographerDto extends UserDto {
        private String cameraUsed;
    
        public PhotographerDto(Long id, String userName, String fullName, String email, String password, String address, String userType, String cameraUsed) {
            super(id, userName, fullName, email, password, address,userType);
            this.cameraUsed = cameraUsed;
        }
    
        public String getCameraUsed() {
            return cameraUsed;
        }
    
        public void setCameraUsed(String cameraUsed) {
            this.cameraUsed = cameraUsed;
        }
    }
    public class ReviewerDto extends UserDto {
        private boolean approval;
    
        public ReviewerDto(Long id, String userName, String fullName, String email, String password, String address, String userType, boolean approval) {
            super(id, userName, fullName, email, password, address,userType);
            this.approval = approval;
        }
    
        public boolean getCameraUsed() {
            return approval;
        }
    
        public void setCameraUsed(boolean approval) {
            this.approval= approval;
        }
    }
    public class AdminDto extends UserDto {
        private String department;
    
        public AdminDto(Long id, String userName, String fullName, String email, String password, String address, String userType, String department) {
            super(id, userName, fullName, email, password, address,userType);
            this.department = department;
        }
    
        public String department() {
            return department;
        }
    
        public void setCameraUsed(String department) {
            this.department = department;
        }
    }
    
    
}
