package com.nukkitx.protocol.bedrock.v361.serializer;

import com.nukkitx.network.VarInts;
import com.nukkitx.protocol.bedrock.BedrockPacketHelper;
import com.nukkitx.protocol.bedrock.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.packet.ClientCacheMissResponsePacket;
import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Map;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ClientCacheMissResponseSerializer_v361 implements BedrockPacketSerializer<ClientCacheMissResponsePacket> {
    public static final ClientCacheMissResponseSerializer_v361 INSTANCE = new ClientCacheMissResponseSerializer_v361();

    @Override
    public void serialize(ByteBuf buffer, BedrockPacketHelper helper, ClientCacheMissResponsePacket packet) {
        Map<Long, byte[]> blobs = packet.getBlobs();

        VarInts.writeUnsignedInt(buffer, blobs.size());
        for (Map.Entry<Long, byte[]> entry : blobs.entrySet()) {
            buffer.writeLongLE(entry.getKey());
            helper.writeByteArray(buffer, entry.getValue());
        }
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockPacketHelper helper, ClientCacheMissResponsePacket packet) {
        Map<Long, byte[]> blobs = packet.getBlobs();

        int length = VarInts.readUnsignedInt(buffer);
        for (int i = 0; i < length; i++) {
            long id = buffer.readLongLE();
            byte[] blob = helper.readByteArray(buffer);
            blobs.put(id, blob);
        }
    }
}
