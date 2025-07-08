const LoggoutPage = () => {
  return (
    <div className="flex flex-col items-center justify-center h-screen">
      <h1 className="text-4xl font-bold mb-4">You have been logged out</h1>
      <p className="text-lg mb-8">Thank you for using our application!</p>
      <a href="/" className="text-blue-500 hover:underline">
        Go back to home
      </a>
    </div>
  );
};

export default LoggoutPage;
