package jcm2606.mods.sorcerycraft;


public class SCCommands{
/*	@ApexUniversalCommand()
	public static ApexCommand commandVersion = new ApexCommand("version");
	
	@ApexUniversalCommand
	public static ApexCommand commandCharmCurseApply = new ApexCommand("curse_apply");
	
	@ApexUniversalCommand
	public static ApexCommand commandCharmCurseList = new ApexCommand("curse_list");
	
	@CommandFunction("version")
	public static void version(ApexCommandCalledEvent event){
		event.getSender().sendChatToPlayer("SorceryCraft " + SorceryCraft.version);
	}
	
	@CommandFunction("curse_list")
	public static void curseList(ApexCommandCalledEvent event){
		Set<String> curseNameList = ItemCharm.curseList.keySet();
		String splitter = "";
		String message;
		
		message = curseNameList.toString().replace("[", "").replace("]", "");
		
		event.getSender().sendChatToPlayer("Curses currently available: " + message);
	}
	
	@CommandFunction("curse_apply")
	public static void curseApply(ApexCommandCalledEvent event){
		if(event.getSender().getCommandSenderName().equals("Server"))
		{
			event.getSender().sendChatToPlayer("ERROR: This command cannot be performed via server console.");
			return;
		}
		
		String[] args = event.getArgs();
		
		if(args[0].equals("CLEANSE"))
		{
			ItemStack currentItem = ServerUtil.getPlayer(event.getSender().getCommandSenderName()).getCurrentEquippedItem();
			
			if(currentItem != null)
			{
				if(currentItem.getItem() instanceof ItemCharm)
				{
					ItemCharm charm = (ItemCharm) currentItem.getItem();
					
					if(!charm.getCurseName(currentItem).equals(""))
					{
						charm.setCurseName(currentItem, "");
						charm.setCurseOwner(currentItem, "");
						event.getSender().sendChatToPlayer("Charm has successfully been cleansed of all curses.");
						System.out.println("CHARM @ SLOT " + ServerUtil.getPlayer(event.getSender().getCommandSenderName()).inventory.currentItem + " ON PLAYER '" + event.getSender().getCommandSenderName().toUpperCase() + "' WAS CLEANSED.");
					} else {
						event.getSender().sendChatToPlayer("\247cERROR: Charm must be cursed to cleanse it.");
					}
				} else {
					event.getSender().sendChatToPlayer("\247cERROR: You must be holding a charm to cleanse it.");
				}
			} else {
				event.getSender().sendChatToPlayer("\247cERROR: You must be holding a charm to cleanse it.");
			}
			
			return;
		}
		
		if(ItemCharm.curseList.get(args[0]) != null)
		{
			ItemStack currentItem = ServerUtil.getPlayer(event.getSender().getCommandSenderName()).getCurrentEquippedItem();
			
			if(currentItem != null)
			{
				if(currentItem.getItem() instanceof ItemCharm)
				{
					ItemCharm charm = (ItemCharm) currentItem.getItem();
					
					if(charm.getCurseName(currentItem).equals(args[0]))
					{
						event.getSender().sendChatToPlayer("That charm already has the curse '" + args[0] + "'.");
					} else {
						charm.setCurseName(currentItem, args[0]);
						charm.setCurseOwner(currentItem, event.getSender().getCommandSenderName());
						event.getSender().sendChatToPlayer("Charm has successfully been cursed with '" + args[0] + "'.");
						System.out.println("CHARM @ SLOT " + ServerUtil.getPlayer(event.getSender().getCommandSenderName()).inventory.currentItem + " ON PLAYER '" + event.getSender().getCommandSenderName().toUpperCase() + "' HAD CURSE '" + args[0].toUpperCase() + "' APPLIED.");
					}
				} else {
					event.getSender().sendChatToPlayer("\247cERROR: You must be holding a charm to curse it.");
				}
			} else {
				event.getSender().sendChatToPlayer("\247cERROR: You must be holding a charm to curse it.");
			}
		} else {
			event.getSender().sendChatToPlayer("\247cERROR: Curse '" + args[0] + "' is not valid!");
		}
	}*/
}