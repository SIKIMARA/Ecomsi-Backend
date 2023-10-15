package org.sikimaraservices.orders;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
@Builder
public class OrderDTO {
    private Long id;
    private Date orderDate;
    private int UserId;
    private String FullName;
    private String Email;
    private String Address;
    private String City;
    private String Country;
    private String Phone;
    private String PostalCode;
}
