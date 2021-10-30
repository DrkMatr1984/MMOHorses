package relampagorojo93.MMOHorses.Modules.CommandsPckg.Commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import relampagorojo93.LibsCollection.SpigotCommands.Objects.Command;
import relampagorojo93.LibsCollection.SpigotCommands.Objects.SubCommand;
import relampagorojo93.LibsCollection.SpigotMessages.MessagesUtils;
import relampagorojo93.MMOHorses.API.MMOHorsesAPI;
import relampagorojo93.MMOHorses.Enums.Marking;
import relampagorojo93.MMOHorses.Enums.Type;
import relampagorojo93.MMOHorses.Modules.EntityPckg.Interfaces.Objects.MMOHorse;
import relampagorojo93.MMOHorses.Modules.FilePckg.Messages.MessageString;
import relampagorojo93.MMOHorses.Modules.FilePckg.Settings.SettingList;
import relampagorojo93.MMOHorses.Modules.FilePckg.Settings.SettingString;

public class SetMarkingSubCommand extends SubCommand {
	public SetMarkingSubCommand(Command command) {
		super(command, "setmarking", SettingString.HORSE_SETMARKING_NAME.toString(), SettingString.HORSE_SETMARKING_PERMISSION.toString(),
				SettingString.HORSE_SETMARKING_DESCRIPTION.toString(), SettingString.HORSE_SETMARKING_PARAMETERS.toString(),
				SettingList.HORSE_SETMARKING_ALIASES.toList());
	}

	@Override
	public List<String> tabComplete(Command cmd, CommandSender sender, String[] args) {
		List<String> list = new ArrayList<>();
		switch (args.length) {
			case 1: for (Marking marking:Marking.values()) list.add(marking.name()); break;
			default: break;
		}
		return list;
	}

	@Override
	public boolean execute(Command cmd, CommandSender sender, String[] args, boolean useids) {
		Player p = sender instanceof Player ? (Player) sender : null;
		if (p != null) {
			if (p.getVehicle() != null) {
				MMOHorse ec = MMOHorsesAPI.getUtils().toMMOHorse(p.getVehicle());
				if (ec != null && ec.getType() == Type.HORSE) {
					if (args.length >= 2) {
						try {
							Marking marking = Marking.valueOf(args[1].toUpperCase());
							ec.getMMOHorseData().setMarking(marking, true);
						} catch (IllegalArgumentException e) {
							MessagesUtils.getMessageBuilder().createMessage(MMOHorsesAPI.getUtils()
									.applyPrefix("Available markings: Socks, Pinto, Roan, Baldface, None")).sendMessage(sender);
						}
					} else
						MessagesUtils.getMessageBuilder().createMessage(MMOHorsesAPI.getUtils().applyPrefix(getUsage())).sendMessage(sender);
				} else
					MessagesUtils.getMessageBuilder().createMessage(MMOHorsesAPI.getUtils().applyPrefix(MessageString.ENTITYNOTSUPPORTSFEATURE)).sendMessage(sender);
			} else
				MessagesUtils.getMessageBuilder().createMessage(MMOHorsesAPI.getUtils().applyPrefix(MessageString.NOTRIDING)).sendMessage(sender);
		} else
			MessagesUtils.getMessageBuilder().createMessage(MMOHorsesAPI.getUtils().applyPrefix(MessageString.CONSOLEDENIED)).sendMessage(sender);
		return true;
	}
}
