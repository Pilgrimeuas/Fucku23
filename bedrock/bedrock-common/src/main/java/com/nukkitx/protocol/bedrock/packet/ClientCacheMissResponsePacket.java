package com.nukkitx.protocol.bedrock.packet;

import com.nukkitx.protocol.bedrock.BedrockPacket;
import com.nukkitx.protocol.bedrock.BedrockPacketType;
import com.nukkitx.protocol.bedrock.handler.BedrockPacketHandler;
import lombok.Data;

import java.util.*;

@Data
public class ClientCacheMissResponsePacket extends BedrockPacket {
    private final Map<Long, byte[]> blobs = new HashMap<>();

    @Override
    public boolean handle(BedrockPacketHandler handler) {
        return handler.handle(this);
    }

    public BedrockPacketType getPacketType() {
        return BedrockPacketType.CLIENT_CACHE_MISS_RESPONSE;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof ClientCacheMissResponsePacket)) return false;
        final ClientCacheMissResponsePacket other = (ClientCacheMissResponsePacket) o;

        Iterator<Map.Entry<Long, byte[]>> thisIterator = this.blobs.entrySet().iterator();

        for (Map.Entry<Long, byte[]> thatEntry : other.blobs.entrySet()) {
            if (!thisIterator.hasNext()) {
                return false;
            }
            Map.Entry<Long, byte[]> thisEntry = thisIterator.next();
            if (!Objects.equals(thisEntry.getKey(), thatEntry.getKey()) ||
                    !Arrays.equals(thisEntry.getValue(), thatEntry.getValue())) {
                return false;
            }
        }
        return true;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        for (Map.Entry<Long, byte[]> entry : this.blobs.entrySet()) {
            result = result * PRIME + Long.hashCode(entry.getKey());
            result = result * PRIME + Arrays.hashCode(entry.getValue());
        }
        return result;
    }
}
