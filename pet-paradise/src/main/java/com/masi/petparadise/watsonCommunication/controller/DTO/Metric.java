package com.masi.petparadise.watsonCommunication.controller.DTO;

import lombok.Data;

@Data
public class Metric {
    int ACL;
    double QEI;
    int CFI;
    int CUS;
    int CES;

    public Metric() {
        ACL = 0;
        QEI = 0;
        CFI = 0;
        CUS = 0;
        CES = 0;
    }
}
