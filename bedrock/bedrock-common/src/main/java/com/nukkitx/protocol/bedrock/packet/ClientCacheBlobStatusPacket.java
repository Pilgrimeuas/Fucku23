package com.nukkitx.protocol.bedrock.packet;

import com.nukkitx.protocol.bedrock.BedrockPacket;
import com.nukkitx.protocol.bedrock.BedrockPacketType;
import com.nukkitx.protocol.bedrock.handler.BedrockPacketHandler;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.ArrayList;

@Data
@EqualsAndHashCode(doNotUseGetters = true, callSuper = false)
public class ClientCacheBlobStatusPacket extends BedrockPacket {
    private final ArrayList<Long> acks = new ArrayList<>();
    private final ArrayList<Long> naks = new ArrayList<>();

    @Override
    public boolean handle(BedrockPacketHandler handler) {
        return handler.handle(this);
    }

    public BedrockPacketType getPacketType() {
        return BedrockPacketType.CLIENT_CACHE_BLOB_STATUS;
    }
}
