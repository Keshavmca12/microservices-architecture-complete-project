package com.tga.product.command;

import lombok.Data;
import lombok.ToString;


@ToString
@Data
public class PublishProductCommand extends ProductCommand{
    private boolean publish;
}
