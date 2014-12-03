/*
 * Chomp strategy
 */

public interface Strategy
{
  Location findBestMove(ChompGame game);
  Location findRandomMove(ChompGame game);
}
